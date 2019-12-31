package com.vesnadev.app.ws.exceptions;

public class UserServiceException extends RuntimeException{

    private static final long serialVersionUID = 2524035544122045219L;

    public UserServiceException(String message) {
        super(message);
    }

}
