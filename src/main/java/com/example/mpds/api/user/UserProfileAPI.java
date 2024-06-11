package com.example.mpds.api.user;

import com.example.mpds.api.output.CartOutput;
import com.example.mpds.dto.InvoiceDTO;
import com.example.mpds.dto.UserDTO;
import com.example.mpds.services.impl.InvoiceService;
import com.example.mpds.services.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserProfileAPI {
    private final UserService userService;
    private final InvoiceService invoiceService;
    @PostMapping(value = "/profile")
    public String loginPage(@RequestBody Long userId, Model model){
        UserDTO userDTO=userService.findByUserId(userId);
        List<InvoiceDTO> invoiceDTOS= invoiceService.findAllByUserId(userId);
        model.addAttribute("user",userDTO);
        model.addAttribute("invoices",invoiceDTOS);
        return "userProfile";
    }
//    @PostMapping(value = "/profile")
//    public ResponseEntity<UserDTO> updateUserProfile(@RequestBody UserDTO userDTO){
//        return ResponseEntity.ok(userService.updateProfile(userDTO));
//    }
}
