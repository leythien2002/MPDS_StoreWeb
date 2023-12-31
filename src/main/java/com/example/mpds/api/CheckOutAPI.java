package com.example.mpds.api;

import com.example.mpds.dto.*;
import com.example.mpds.entity.InvoiceEntity;
import com.example.mpds.entity.UserEntity;
import com.example.mpds.services.impl.InvoiceInfoService;
import com.example.mpds.services.impl.InvoiceService;
import com.example.mpds.services.impl.ProductService;
import com.example.mpds.services.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CheckOutAPI {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceInfoService invoiceInfoService;





    @GetMapping(value = "/checkout")
    public String showCheckOutPage(){
        return "checkout";
    }

    @PostMapping(value = "/checkout")
    public RedirectView saveInvoice(@RequestParam(value = "totalMoney")double total,
                                    @RequestParam(value = "phone")String phone,
                                    @RequestParam(value = "address")String address,
                                    @RequestParam(value = "email")String email,
                                    @RequestParam(value = "status")String status,

                                    HttpSession session
    ){
        CartDTO cart= (CartDTO) session.getAttribute("cart");
        HashMap<Integer,CartItemDTO> list=cart.getItemList();
//        String userName= (String) session.getAttribute("username");

        InvoiceDTO dto=new InvoiceDTO();

        dto.setPhone(phone);
        dto.setStatus(status);
        dto.setEmail(email);
        dto.setTotalMoney(total);
        dto.setAddress(address);
        //setUserId-->setUserName de controller nhan duoc.
        InvoiceEntity entity=invoiceService.save(dto);
        //detail invoice
        for(Map.Entry<Integer,CartItemDTO> item: list.entrySet()){
            InvoiceInfoDTO infoDTO=new InvoiceInfoDTO();


            infoDTO.setProductId(item.getValue().getProduct().getId());
            infoDTO.setInvoiceId(entity.getId());
            infoDTO.setAmount(item.getValue().getQuantity());
            infoDTO.setPrice(item.getValue().getPrice());
            invoiceInfoService.save(infoDTO);
        }
        return new RedirectView("/");
    }


}
