package com.gituhub.chavesrodolfo.campaign.exceptions;

public class CampaignNotAcceptedException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public CampaignNotAcceptedException() {
        super(String.format("Campaign cannot be accepted")); 
    }
}
