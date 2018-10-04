package com.example.bootdemo.exceptions;


public class RecoverableException extends DemoException {

    /**
     * @param message   : message
     * @param throwable : throwable
     * @implNote Two Parameter pass constructor
     */
    public RecoverableException(String message, Throwable throwable) {
        super(message, throwable);
    }


}
