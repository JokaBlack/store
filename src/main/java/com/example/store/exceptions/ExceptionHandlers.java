package com.example.store.exceptions;

import com.example.store.controllers.UserController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {UserController.class})
public class ExceptionHandlers {

    @ExceptionHandler(value = {SomeExceprion.class})
    public String exceptHandler(SomeExceprion someExceprion){
        return someExceprion.getMessage();
    }
}
