package com.example.bootdemo.exceptions;


public class UnrecoverableException extends DemoException {

    /**
     * @param throwable : throwable
     * @implNote Constructor in pass Throwable class object
     */
    public UnrecoverableException(Throwable throwable) {
        super(throwable);
    }
}
