package com.gituhub.chavesrodolfo.campaign.controller;

import java.util.List;

import com.gituhub.chavesrodolfo.campaign.exceptions.CampaignNotAcceptedException;
import com.gituhub.chavesrodolfo.campaign.model.representations.CampaignRepresentation;
import com.gituhub.chavesrodolfo.campaign.service.CampaignService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CampaignController {

    @Autowired
    CampaignService campaignService;

    @GetMapping("/hello")
    public String hello() {
        return "Campaigns API";
    }

    @PostMapping("/campaigns")
    public CampaignRepresentation createCampaign(@RequestBody CampaignRepresentation campaignRepresentation) throws CampaignNotAcceptedException{
        campaignRepresentation = campaignService.create(campaignRepresentation);
        return campaignRepresentation;
    }

    @GetMapping("/campaigns")
    public List<CampaignRepresentation> listCampaigns() {
        List<CampaignRepresentation> campaigns = campaignService.listAllValid();
        return campaigns;
    }

    @GetMapping("/campaigns/{id}")
    public CampaignRepresentation findCampaign(@PathVariable("id") Long id) throws Exception {
        CampaignRepresentation campaign = campaignService.findById(id);
        return campaign;
    }

    @PutMapping("/campaigns/{id}")
    public CampaignRepresentation updateCampaign(@PathVariable("id") Long id,
            @RequestBody CampaignRepresentation campaignRepresentation) throws Exception {
        CampaignRepresentation campaign = campaignService.update(id, campaignRepresentation);
        return campaign;
    }

    @DeleteMapping("/campaigns/{id}")
    public void deleteCampaign(@PathVariable("id") Long id) throws Exception {
        campaignService.delete(id);
    }
}
