package com.gituhub.chavesrodolfo.customer.exceptions;

public class CustomerCreatedException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public CustomerCreatedException() {
        super(String.format("Customer already exists")); 
    }
}
