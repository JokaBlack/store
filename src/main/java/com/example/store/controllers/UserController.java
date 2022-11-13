package com.example.store.controllers;

import com.example.store.services.UserService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;


@Controller
@AllArgsConstructor
public class UserController {
    private final UserService service;

}
