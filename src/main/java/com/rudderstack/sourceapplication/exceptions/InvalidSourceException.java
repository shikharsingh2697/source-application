package com.rudderstack.sourceapplication.exceptions;

public class InvalidSourceException extends RuntimeException {
    public InvalidSourceException(String message){
        super(message);
    }
}