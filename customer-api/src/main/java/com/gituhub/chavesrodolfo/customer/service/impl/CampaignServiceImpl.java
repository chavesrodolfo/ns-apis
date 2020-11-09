package com.gituhub.chavesrodolfo.customer.service.impl;

import java.io.IOException;

import com.gituhub.chavesrodolfo.customer.service.CampaignService;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CampaignServiceImpl implements CampaignService {

    @Value("${app.campaigns.url}")
    private String urlServiceListCampaigns;

    @Override
    public String fetchCampaignsFromAPI(Long heartClub) throws ClientProtocolException, IOException {
        log.info("Fetching campaigns from Campaigns API");

        HttpGet request = new HttpGet(urlServiceListCampaigns);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        
        HttpResponse response = httpClient.execute(request);
        String bodyJson = EntityUtils.toString(response.getEntity());

        JSONArray array = new JSONArray(bodyJson);
        
        String campaigns = "";
        JSONObject obj;

        for (int i = 0; i < array.length(); i++) {
            
            obj = array.getJSONObject(i);
            
            if(obj.getInt("heartClub") != heartClub)
                continue;
            
            if (i==0)
                campaigns += obj.getInt("id");
            else
                campaigns += "," + obj.getInt("id");
        }

        log.info("Campaigns: " + campaigns);

        return campaigns;
    }

}
