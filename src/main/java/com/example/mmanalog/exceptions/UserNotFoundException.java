package com.example.mmanalog.exceptions;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {super();}

    public UserNotFoundException(String username) {
        super("Cannot find user with " + username);
    }
}
