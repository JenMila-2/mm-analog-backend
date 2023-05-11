package com.example.mmanalog.exceptions;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String email) {
        super("Cannot find a user with " + email);
    }
}
