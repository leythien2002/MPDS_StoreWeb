package com.example.mpds.api.user;

import com.example.mpds.api.output.CartOutput;
import com.example.mpds.dto.InvoiceDTO;
import com.example.mpds.dto.UserDTO;
import com.example.mpds.model.UserInvoiceResult;
import com.example.mpds.services.impl.InvoiceService;
import com.example.mpds.services.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class UserProfileAPI {
    private final UserService userService;
    private final InvoiceService invoiceService;
    @GetMapping(value = "/profile/{userId}")
    public String loginPage(@PathVariable Long userId, Model model){
        UserDTO userDTO=userService.findByUserId(userId);
//        List<InvoiceDTO> invoiceDTOS= invoiceService.findAllByUserId(userId);
        model.addAttribute("user",userDTO);
        Pageable pageable = Pageable.ofSize(2).withPage(0);
        UserInvoiceResult invoicePage = invoiceService.findAllByUserId(userId,pageable);

        Map<String, Object> response = new HashMap<>();
        model.addAttribute("invoices", invoicePage.getInvoices());
        model.addAttribute("totalPage", invoicePage.getTotalPage());
        model.addAttribute("currentPage", 1);
//        model.addAttribute("invoices",invoiceDTOS);
        return "userProfile";
    }
    @GetMapping(value = "/profile/{userId}/invoices")
    @ResponseBody
    public Map<String, Object> getInvoices(@RequestParam("page") int page, @PathVariable Long userId) {
        int pageSize = 2; // Số lượng mục mỗi trang
        Pageable pageable = Pageable.ofSize(pageSize).withPage(page);
        UserInvoiceResult invoicePage = invoiceService.findAllByUserId(userId,pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("invoice", invoicePage.getInvoices());
        response.put("totalPages", invoicePage.getTotalPage());
        response.put("currentPage", page);
        return response;
    }

    @PostMapping("/profile/changepassword")
    public ResponseEntity<?> changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 @RequestParam("userId") Long userId
                                 ) {
        System.out.println(oldPassword);
        System.out.println(newPassword);
        System.out.println(confirmPassword);

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Confirm password do not match");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("New password and confirm password do not match");
        }

        UserDTO userDTO=userService.findByUserId(userId);
        System.out.println(userDTO.getUserName());
        System.out.println(userDTO.getPassword());


        if (!userDTO.getPassword().equals(oldPassword)) {
            System.out.println("Old password is incorrect");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Old password is incorrect");
        }
        userDTO.setPassword(newPassword);
        userService.updatePassword(userDTO);
        System.out.println("success");

        return ResponseEntity.ok("Password updated successfully");
    }
        @PostMapping(value = "/profile")
    public ResponseEntity<UserDTO> updateUserProfile(@RequestBody UserDTO userDTO){
//            System.out.println("hereeeee"+userDTO.getUserName());

            return ResponseEntity.ok(userService.updateProfile(userDTO));
    }
}
