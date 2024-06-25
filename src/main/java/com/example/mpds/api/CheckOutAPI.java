package com.example.mpds.api;

import com.example.mpds.dto.*;
import com.example.mpds.entity.InvoiceEntity;
import com.example.mpds.entity.InvoiceInfoEntity;
import com.example.mpds.entity.UserEntity;
import com.example.mpds.services.impl.InvoiceInfoService;
import com.example.mpds.services.impl.InvoiceService;
import com.example.mpds.services.impl.ProductService;
import com.example.mpds.services.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public String saveInvoice(@RequestParam(value = "totalMoney")double total,
                                    @RequestParam(value = "phone")String phone,
                                    @RequestParam(value = "address")String address,
                                    @RequestParam(value = "email")String email,
                                    @RequestParam(value = "status")String status,
                                    @RequestParam(value = "vnPayChecked") boolean vnPayChecked,
                                    Model model,
                                    HttpSession session
    ) throws ParseException {
        CartDTO cart= (CartDTO) session.getAttribute("cart");
        HashMap<Integer,CartItemDTO> list=cart.getItemList();
        int userId= (int) session.getAttribute("userId");
        String userName= (String) session.getAttribute("username");
        InvoiceDTO dto=new InvoiceDTO();

        dto.setPhone(phone);
        dto.setStatus(status);
        dto.setEmail(email);
        dto.setTotalMoney(total);
        dto.setAddress(address);
        dto.setCreatedBy(userName);

        if(vnPayChecked) dto.setPaymentMethod("VNPay");
        else dto.setPaymentMethod("COD");


        //setUserId-->setUserName de controller nhan duoc.
        InvoiceEntity entity=invoiceService.save(dto,userId);
        List<InvoiceInfoEntity> listInfo=new ArrayList<>();
        //detail invoice
        for(Map.Entry<Integer,CartItemDTO> item: list.entrySet()){
            InvoiceInfoDTO infoDTO=new InvoiceInfoDTO();


            infoDTO.setProductId(item.getValue().getProduct().getId());
            infoDTO.setInvoiceId(entity.getId());
            infoDTO.setAmount(item.getValue().getQuantity());
            infoDTO.setPrice(item.getValue().getPrice());
            listInfo.add( invoiceInfoService.save(infoDTO));
        }

        HashMap<Integer,CartItemDTO> newCart = new HashMap<Integer,CartItemDTO>();
        cart.setItemList(newCart);
        cart.setTotal(0);
        entity.setListInvoiceInfo(listInfo);
        model.addAttribute("invoiceDetail",entity);
        if(vnPayChecked){
            return "redirect:/create_payment?totalMoney=" + total;
//            return new RedirectView(onlinePaymentUrl);
        }
        return "orderDetail";
    }
    private Date formatDate(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatter.parse(formatter.format(date));
    }


}
