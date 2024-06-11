package com.example.mpds.api;

import com.example.mpds.dto.UserDTO;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.entity.ProductReviewEntity;
import com.example.mpds.model.UserInvoiceResult;
import com.example.mpds.repository.ProductReviewRepository;
import com.example.mpds.services.impl.InvoiceInfoService;
import com.example.mpds.services.impl.InvoiceService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class InvoiceAPI {
    private final InvoiceService invoiceService;
    private final ProductReviewRepository productReviewRepository;
    private final InvoiceInfoService invoiceInfoService;
    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Review{
        private int productId;
        private String review;
        private int userId;
    }
    @GetMapping(value = "/invoice/{id}")
    public String loginPage(@PathVariable Long id, Model model){
        List<ProductEntity> products=invoiceInfoService.getInfo(id);
        model.addAttribute("products", products);
////        List<InvoiceDTO> invoiceDTOS= invoiceService.findAllByUserId(userId);
//        model.addAttribute("user",userDTO);
//        Pageable pageable = Pageable.ofSize(2).withPage(0);
//        UserInvoiceResult invoicePage = invoiceService.findAllByUserId(userId,pageable);
//
//        Map<String, Object> response = new HashMap<>();
//        model.addAttribute("invoices", invoicePage.getInvoices());
//        model.addAttribute("totalPage", invoicePage.getTotalPage());
//        model.addAttribute("currentPage", 1);
////        model.addAttribute("invoices",invoiceDTOS);
        return "invoiceDetail";
    }
    @PostMapping(value = "/review")
    public ResponseEntity<ProductReviewEntity> test(@RequestBody Review review){
        return ResponseEntity.ok(productReviewRepository.save(new ProductReviewEntity
                (review.getProductId(),review.getUserId(),review.getReview())));

    }

}
