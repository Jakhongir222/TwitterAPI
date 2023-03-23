package com.example.twitter.exceptions;

public class UserDoesNotExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserDoesNotExistException() {
        super("User that you are looking for does not exist");
    }
}
