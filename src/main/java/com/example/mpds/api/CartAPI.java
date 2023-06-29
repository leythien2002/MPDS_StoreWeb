package com.example.mpds.api;

import com.example.mpds.api.output.CartOutput;
import com.example.mpds.dto.CartDTO;
import com.example.mpds.dto.CartItemDTO;
import com.example.mpds.dto.ProductDTO;

import com.example.mpds.services.impl.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
public class CartAPI {
    @Autowired
    private ProductService productService;
    @PostMapping(value = "/cart/add")
    public ResponseEntity<CartOutput> addProduct(@RequestParam(value = "id")int id,
                                             @RequestParam(value = "quantity") int quantity,
                                             HttpSession session){
        CartOutput response=new CartOutput();

        CartItemDTO item=new CartItemDTO();
        ProductDTO product=productService.findOne(id);
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setPrice(product.getPrice());
        //init Cart
        CartDTO cart= (CartDTO) session.getAttribute("cart");
        if(cart==null){
            cart=new CartDTO();
        }
        HashMap<Integer,CartItemDTO> tmp=cart.getItemList();
        if(tmp==null){
            tmp=new HashMap<>();
        }
        tmp.put(product.getId(),item);
        cart.setItemList(tmp);

        int cartSize= tmp.size();
        //calculate total price of cart;
        double totalPriceOfCart=0;
        for(CartItemDTO i:cart.getItemList().values()){
            totalPriceOfCart+=(i.getPrice()*i.getQuantity());
        }
        cart.setTotal(totalPriceOfCart);
        response.setCartSize(cartSize);

        session.setAttribute("cart",cart);
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/cart/amount")
    public ResponseEntity<CartOutput> changeQuantityItem(@RequestParam(value = "id")int id,
                                              @RequestParam(value = "quantity") int quantity,
                                              @RequestParam(value = "change")String change,
                                              HttpSession session){

//        HashMap<Integer,CartItemDTO>cart= (HashMap<Integer, CartItemDTO>) session.getAttribute("cart");
        CartOutput response=new CartOutput();

        CartDTO cart= (CartDTO) session.getAttribute("cart");
        double totalPriceOfCart=cart.getTotal();

        if(cart.getItemList().containsKey(id)){
            CartItemDTO item=cart.getItemList().get(id);
            if(change.equals("inc")){
                item.setQuantity(item.getQuantity()+1);
                totalPriceOfCart+= item.getPrice();


                response.setQuantityProduct(item.getQuantity());
            }
            else{
                if(item.getQuantity()-1<1){
                    response.setQuantityProduct(item.getQuantity());
                }
                else{
                    item.setQuantity(item.getQuantity()-1);
                    totalPriceOfCart-= item.getPrice();


                    response.setQuantityProduct(item.getQuantity());
                }
            }
            double total= (double) (item.getProduct().getPrice()* item.getQuantity());
            response.setTotalPrice(total);
            cart.getItemList().put(id,item);
        }
        int cartSize= cart.getItemList().size();
        //calculate total price of cart;
        cart.setTotal(totalPriceOfCart);
        response.setTotalPriceOfCart(totalPriceOfCart);
        response.setCartSize(cartSize);

        session.setAttribute("cart",cart);
        return ResponseEntity.ok(response);

    }
    @DeleteMapping(value = "/cart/remove")
    public String removeItem(@RequestParam(value = "id")int id,HttpSession session){
        CartOutput response=new CartOutput();
        CartDTO cart= (CartDTO) session.getAttribute("cart");
        if(cart.getItemList().containsKey(id)){
            cart.getItemList().remove(id);
        }
        session.setAttribute("cart",cart);
        return "cart";
    }
    @GetMapping(value = "/cart")
    public String getCart(Model model){
        //active class in html
        String a="/cart";
        model.addAttribute("query",a);
        return "cart";
    }
}
