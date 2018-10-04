package com.example.bootdemo.exceptions;


public class InvalidTokenException extends RecoverableException {



    public InvalidTokenException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
