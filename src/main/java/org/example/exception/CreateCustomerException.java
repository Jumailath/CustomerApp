package org.example.exception;


public class CreateCustomerException extends RuntimeException{
    public CreateCustomerException(String message) {
        super(message);
    }
}
