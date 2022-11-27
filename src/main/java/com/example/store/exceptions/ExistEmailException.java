package com.example.store.exceptions;

public class ExistEmailException extends RuntimeException{
    public ExistEmailException(String errorMessage) {
        super(errorMessage);
    }
}
