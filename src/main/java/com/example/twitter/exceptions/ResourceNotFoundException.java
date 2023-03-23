package com.example.twitter.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String user, String id, Integer userId) {
        super(String.format( user + "not found with" + userId + id));
    }
}
