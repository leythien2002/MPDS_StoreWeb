package com.example.mpds.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
@AllArgsConstructor
public class ErrorController {
    @GetMapping
    public String errorPage(){
        return "error";
    }
}
