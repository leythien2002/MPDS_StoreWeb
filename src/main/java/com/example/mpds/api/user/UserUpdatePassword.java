package com.example.mpds.api.user;

import com.example.mpds.component.OTPUtil;
import com.example.mpds.dto.UserDTO;
import com.example.mpds.entity.UserEntity;
import com.example.mpds.services.impl.EmailService;
import com.example.mpds.services.impl.OTPService;
import com.example.mpds.services.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@AllArgsConstructor
@RequestMapping("/update-password")
public class UserUpdatePassword {

    @Autowired
    UserService userService;
    @Autowired
    private OTPService otpService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public String get() {
        return "enterusername";
    }

    @PostMapping(value = "/generate-otp")
    public String generateOTP(@RequestParam(value = "username") String username, Model model) {
        try {
            UserDTO existedUSer = userService.findUserByUserName(username);
            String otp = OTPUtil.generateOTP();
            otpService.storeOTP(existedUSer.getEmail(), otp);
            emailService.sendSimpleEmail(existedUSer.getEmail(), "Your OTP Code", "Your OTP code is: " + otp);
            model.addAttribute("email", existedUSer.getEmail());
            model.addAttribute("username", existedUSer.getUserName());

            return "otpconfirmpassword";
        }
        catch (RuntimeException e){
            System.out.println("User not found!");
            model.addAttribute("errorMessage", "Username is not existed, Try again!");
            return "enterusername";
        }
    }

    @PostMapping(value = "/verify")
    public String verifyOTP(@RequestParam(value = "email") String email,@RequestParam(value = "username") String username,  @RequestParam(value = "otp") String otp, Model model) {
        String storedOtp = otpService.getOTP(email);
        if (storedOtp != null && storedOtp.equals(otp)) {
            model.addAttribute("email", email);
            model.addAttribute("username", username);
            otpService.removeOTP(email);
            return "enternewpassword";

        } else {
            System.out.println("Wrong OTP");
            model.addAttribute("email", email);
            model.addAttribute("username", username);

            model.addAttribute("errorMessage", "Wrong OTP, Try again!");
            return "otpconfirmpassword";
        }
    }
    @PostMapping(value = "/update")
    public RedirectView updatePassword(@RequestParam(value = "username")String username, @RequestParam(value = "newPassword") String newPassword, Model model) {
        UserDTO existedUSer = userService.findUserByUserName(username);
        System.out.println("New Password: "+newPassword);

        existedUSer.setPassword(newPassword);
        userService.updatePassword(existedUSer);
            return new RedirectView("/login");
    }
}
