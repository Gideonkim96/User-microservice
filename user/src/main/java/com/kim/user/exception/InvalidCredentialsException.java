package com.kim.user.exception;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super("Invalid username or password. Please check your credentials.");
    }
}
