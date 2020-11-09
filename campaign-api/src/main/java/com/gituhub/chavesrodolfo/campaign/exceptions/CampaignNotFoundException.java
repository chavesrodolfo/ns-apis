package com.gituhub.chavesrodolfo.campaign.exceptions;

public class CampaignNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public CampaignNotFoundException(Long id) {
        super(String.format("Could not find campaign %s", id)); 
    }
}
