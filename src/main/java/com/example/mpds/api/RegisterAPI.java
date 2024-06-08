package com.example.mpds.api;

import com.example.mpds.dto.UserDTO;
import com.example.mpds.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RegisterAPI {
    @Autowired
    private UserService userService;
    @GetMapping(value = "/register")
    public String regisPage(){
        return "register";
    }
    @PostMapping(value = "/register/add")
    public RedirectView register(@RequestParam(value = "name")String name,
                           @RequestParam(value = "email")String email,
                           @RequestParam(value = "userName")String userName,
                           @RequestParam(value = "password")String password,
                           @RequestParam(value = "permission")int permission

                           ){
        System.out.println(password);
        System.out.println(name);
        System.out.println(email);
        System.out.println(userName);
        System.out.println(permission);

        UserDTO userDTO= new UserDTO();
        userDTO.setUserName(userName);
        userDTO.setEmail(email);
        userDTO.setName(name);
        userDTO.setPermission(0);
        userDTO.setPassword(password);
        userService.save(userDTO);
        return new RedirectView("/");
    }
}
