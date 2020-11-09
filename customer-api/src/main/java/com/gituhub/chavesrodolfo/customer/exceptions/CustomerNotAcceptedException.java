package com.gituhub.chavesrodolfo.customer.exceptions;

public class CustomerNotAcceptedException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public CustomerNotAcceptedException() {
        super(String.format("Customer cannot be accepted")); 
    }
}
