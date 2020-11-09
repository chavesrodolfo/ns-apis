package com.gituhub.chavesrodolfo.customer.service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface CampaignService {
    
    public String fetchCampaignsFromAPI(Long heartClub) throws ClientProtocolException, IOException;
}
