package com.gituhub.chavesrodolfo.campaign.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.gituhub.chavesrodolfo.campaign.model.Campaign;
import com.gituhub.chavesrodolfo.campaign.model.CampaignRepresentation;
import com.gituhub.chavesrodolfo.campaign.repository.CampaignRepository;
import com.gituhub.chavesrodolfo.campaign.service.CampaignService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    CampaignRepository campaignRepository;

    @Override
    public CampaignRepresentation create(CampaignRepresentation campaignRepresentation) {
        log.info("Creating Campaign {}", campaignRepresentation.getName());

        Campaign campaign = new Campaign(campaignRepresentation);
        campaign = campaignRepository.save(campaign);
        return campaign.transformToRepresentation();
    }

    @Override
    public List<CampaignRepresentation> listAllValid() {

        log.info("Listing all valid Campaigns");

        List<Campaign> campaigns = campaignRepository.findAllValidCampaigns();
        List<CampaignRepresentation> campaignsRep = new ArrayList<CampaignRepresentation>();

        for (Campaign campaign : campaigns) {
            CampaignRepresentation campaignRepresentation = campaign.transformToRepresentation();
            campaignsRep.add(campaignRepresentation);
        }

        return campaignsRep;
    }

    @Override
    public CampaignRepresentation findById(Long id) throws Exception {
        log.info("Listing Campaign id {}", id);

        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new Exception());
        CampaignRepresentation campaignRepresentation = campaign.transformToRepresentation();
        return campaignRepresentation;
    }

    @Override
    public CampaignRepresentation update(Long id, CampaignRepresentation campaignRepresentation) throws Exception {
        log.info("Updating Campaign {}", campaignRepresentation.getName());

        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new Exception());
        
        campaign.setName(campaignRepresentation.getName());
        campaign.setValidity(campaignRepresentation.getValidity());
        campaign.setHeartClub(campaignRepresentation.getHeartClub());

        campaign = campaignRepository.save(campaign);
        
        return campaign.transformToRepresentation();
    }

    @Override
    public void delete(Long id) throws Exception {

        log.info("Deleting Campaign id {}", id);

        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new Exception());
        campaignRepository.delete(campaign);
    }
    
}
