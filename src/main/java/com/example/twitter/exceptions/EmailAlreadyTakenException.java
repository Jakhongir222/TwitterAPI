package com.example.twitter.exceptions;

public class EmailAlreadyTakenException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public EmailAlreadyTakenException() {
        super("Email you have provided is already taken");
    }
}
