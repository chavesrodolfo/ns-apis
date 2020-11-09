package com.gituhub.chavesrodolfo.campaign.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gituhub.chavesrodolfo.campaign.exceptions.CampaignNotAcceptedException;
import com.gituhub.chavesrodolfo.campaign.exceptions.CampaignNotFoundException;
import com.gituhub.chavesrodolfo.campaign.model.Campaign;
import com.gituhub.chavesrodolfo.campaign.model.representations.CampaignRepresentation;
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
    public CampaignRepresentation create(CampaignRepresentation campaignRepresentation)
            throws CampaignNotAcceptedException {
        log.info("Creating Campaign {}", campaignRepresentation.getName());

        verifyCampaignData(campaignRepresentation);
        updateValidityInActiveCampaigns(campaignRepresentation);
        updateValidityForCampaignsWithSameEndDate(campaignRepresentation);

        Campaign campaign = new Campaign(campaignRepresentation);
        campaign = campaignRepository.save(campaign);
        return campaign.transformToRepresentation();
    }

    private void verifyCampaignData(CampaignRepresentation campaignRepresentation) {
        if (campaignRepresentation.getName() == null || campaignRepresentation.getStartDate() == null
                || campaignRepresentation.getEndDate() == null || campaignRepresentation.getHeartClub() == null) {
            throw new CampaignNotAcceptedException();
        }
    }

    /**
     * Verifica se já existe uma campanha ativa para o período (vigência), caso
     * exista uma campanha ou N campanhas associadas no período será somado um dia
     * no término da vigência de cada campanha já existente.
     * 
     * @param startDate
     * @param endDate
     */
    private void updateValidityInActiveCampaigns(CampaignRepresentation newCampaign) {
        List<CampaignRepresentation> campaigns = listAllValid();
        for (CampaignRepresentation campaignRep : campaigns) {
            if (newCampaign.getStartDate().after(campaignRep.getStartDate())
                    && ( newCampaign.getStartDate().before(campaignRep.getEndDate() ))
                    || newCampaign.getEndDate().after(campaignRep.getStartDate())
                            && newCampaign.getEndDate().before(campaignRep.getEndDate())) {

                campaignRep.setEndDate(incementOneDay(campaignRep.getEndDate()));
                campaignRepository.save(new Campaign(campaignRep));

                // TODO Notificar outros sistemas sobre a mudança
            }
        }
    }

    /**
     * Caso a data final da vigência seja igual a outra campanha, deverá ser
     * acrescido um dia a mais de forma que as campanhas não tenham a mesma data de
     * término de vigência.
     */
    private void updateValidityForCampaignsWithSameEndDate(CampaignRepresentation newCampaign) {
        List<CampaignRepresentation> campaigns = listAllValid();
        for (CampaignRepresentation campaignRep : campaigns) {
            if (newCampaign.getEndDate().equals(campaignRep.getEndDate())) {
                newCampaign.setEndDate(incementOneDay(campaignRep.getEndDate()));

                campaignRepository.save(new Campaign(newCampaign));

                // TODO Notificar outros sistemas sobre a mudança
            }
        }
    }

    private Date incementOneDay(Date endDate) {
        LocalDateTime localDatePlus1 = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(1);
        return Date.from(localDatePlus1.atZone(ZoneId.systemDefault()).toInstant());
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
    public CampaignRepresentation findById(Long id) throws CampaignNotFoundException {
        log.info("Listing Campaign id {}", id);

        Campaign campaign = campaignRepository.findValidCampaign(id)
                .orElseThrow(() -> new CampaignNotFoundException(id));
        CampaignRepresentation campaignRepresentation = campaign.transformToRepresentation();
        return campaignRepresentation;
    }

    @Override
    public CampaignRepresentation update(Long id, CampaignRepresentation campaignRepresentation)
            throws CampaignNotFoundException {
        log.info("Updating Campaign {}", campaignRepresentation.getName());

        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException(id));

        campaign.setName(campaignRepresentation.getName());
        campaign.setStartDate(campaignRepresentation.getStartDate());
        campaign.setEndDate(campaignRepresentation.getEndDate());
        campaign.setHeartClub(campaignRepresentation.getHeartClub());

        campaign = campaignRepository.save(campaign);

        return campaign.transformToRepresentation();
    }

    @Override
    public void delete(Long id) throws CampaignNotFoundException {

        log.info("Deleting Campaign id {}", id);

        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException(id));
        campaignRepository.delete(campaign);
    }

}
