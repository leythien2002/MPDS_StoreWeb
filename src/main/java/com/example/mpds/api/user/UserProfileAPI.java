package com.example.mpds.api.user;

import com.example.mpds.dto.UserDTO;
import com.example.mpds.model.UserInvoiceResult;
import com.example.mpds.services.impl.InvoiceService;
import com.example.mpds.services.impl.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class UserProfileAPI {
    private final UserService userService;
    private final InvoiceService invoiceService;
    @GetMapping(value = "/profile")
    public String loginPage( Model model, HttpSession session){
        Integer userId= (Integer) session.getAttribute("userId");

        UserDTO userDTO=userService.findByUserId(userId.longValue());
        int pageSize=2;
//        List<InvoiceDTO> invoiceDTOS= invoiceService.findAllByUserId(userId);
        model.addAttribute("user",userDTO);
        Pageable pageable = Pageable.ofSize(pageSize).withPage(0);
        UserInvoiceResult invoicePage = invoiceService.findAllByUserId(userId,pageable);

        Map<String, Object> response = new HashMap<>();
        model.addAttribute("invoices", invoicePage.getInvoices());
        model.addAttribute("totalPage", Math.round((double) invoicePage.getTotalItem() /pageSize));
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
        response.put("totalPages", invoicePage.getTotalItem());
        response.put("currentPage", page);
        return response;
    }
//    @PostMapping(value = "/profile")
//    public ResponseEntity<UserDTO> updateUserProfile(@RequestBody UserDTO userDTO){
//        return ResponseEntity.ok(userService.updateProfile(userDTO));
//    }
}
