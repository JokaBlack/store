package com.example.store.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class MainController {

    @RequestMapping("/")
    public String mainPage(){
        return "main";
    }
}
