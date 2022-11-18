package com.example.store.controllers;

import com.example.store.dto.UserDto;
import com.example.store.exceptions.SomeExceprion;
import com.example.store.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@Controller
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/user/reg")
    public ResponseEntity<?> regUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new SomeExceprion("User not valid");
        }
        return new ResponseEntity<>(service.regUser(userDto), HttpStatus.OK);
    }
}
