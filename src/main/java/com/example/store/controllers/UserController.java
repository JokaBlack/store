package com.example.store.controllers;

import com.example.store.dto.UserDto;
import com.example.store.exceptions.ExistEmailException;
import com.example.store.exceptions.SomeExceprion;
import com.example.store.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/user/reg")
    public String regUser(@Valid UserDto userDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new SomeExceprion("User not valid");
        }
        try {
            String answerName = service.regUser(userDto);
            return "redirect:/login?name=" + answerName;
        }catch (ExistEmailException e){
            return "redirect:/register?error="+e.getMessage();
        }
    }

    @GetMapping("/login")
    public String loginHandler(@RequestParam(defaultValue = "false") Boolean error,
                               @RequestParam(defaultValue = "") String name, Model model){
        model.addAttribute("error", error);
        model.addAttribute("name", name);
        return "login";
    }

    @GetMapping("/register")
    public String registerHandler(@RequestParam(defaultValue = "") String error,Model model){
        model.addAttribute("error",error);
        return "register";
    }

}
