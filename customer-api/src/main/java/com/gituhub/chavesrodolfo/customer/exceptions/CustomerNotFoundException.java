package com.gituhub.chavesrodolfo.customer.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public CustomerNotFoundException(Long id) {
        super(String.format("Could not find customer %s", id)); 
    }
}
