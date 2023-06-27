package com.example.mpds.api;

import com.example.mpds.dto.CartDTO;
import com.example.mpds.dto.CartItemDTO;
import com.example.mpds.dto.ProductDTO;

import com.example.mpds.services.impl.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
public class CartAPI {
    @Autowired
    private ProductService productService;
    @PostMapping(value = "/cart/add")
    public ResponseEntity<Integer> addProduct(@RequestParam(value = "id")int id,
                                             @RequestParam(value = "quantity") int quantity,
                                             HttpSession session){
        CartItemDTO item=new CartItemDTO();
        ProductDTO product=productService.findOne(id);
        item.setProduct(product);
        item.setQuantity(quantity);
        HashMap<Integer,CartItemDTO>cart= (HashMap<Integer, CartItemDTO>) session.getAttribute("cart");
        if(cart==null){
            cart=new HashMap<>();
        }
        cart.put(product.getId(),item);
        int cartSize= cart.size();
        session.setAttribute("cart",cart);
        return ResponseEntity.ok(cartSize);
    }
    @GetMapping(value = "/cart")
    public String getCart(){
        return "cart";
    }
}
