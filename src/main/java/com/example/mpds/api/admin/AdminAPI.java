package com.example.mpds.api.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminAPI {
    @GetMapping(value = "/admin")
    public String adminPage(){
        return "admin";
    }



}
