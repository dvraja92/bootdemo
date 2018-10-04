package com.example.bootdemo.exceptions;

public class DemoException extends RuntimeException {
    /**
     * @implNote Empty Constructor
     */
    public DemoException() {
    }

    /**
     * @param message : message
     * @implNote Constructor One Parameter Pass
     */
    public DemoException(String message) {
        super(message);
    }

    /**
     * @param message   : message
     * @param throwable : throwable
     * @implNote Two parameter pass constructor
     */
    public DemoException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * @param throwable : throwable
     * @implNote Constructor pass in Throwable class object
     */
    public DemoException(Throwable throwable) {
        super(throwable);
    }

}