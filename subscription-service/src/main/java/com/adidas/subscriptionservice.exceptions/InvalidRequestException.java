package com.adidas.subscriptionservice.exceptions;

public class InvalidRequestException extends Exception {
    public InvalidRequestException(String message){
        super(message);
    }
}
