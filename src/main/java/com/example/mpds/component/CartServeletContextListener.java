package com.example.mpds.component;

import com.example.mpds.dto.CartDTO;
import com.example.mpds.dto.CartItemDTO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CartServeletContextListener implements ServletContextListener {
    //khoi tao mot gio hang ngay khi chay ung dung.
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext= sce.getServletContext();
        HashMap<Integer, CartItemDTO> cart=new HashMap<>();
//        CartDTO cart=new CartDTO();
        servletContext.setAttribute("cart",cart);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce){

    }
}
