package com.gituhub.chavesrodolfo.campaign.service;

import java.util.List;

import com.gituhub.chavesrodolfo.campaign.exceptions.CampaignNotAcceptedException;
import com.gituhub.chavesrodolfo.campaign.exceptions.CampaignNotFoundException;
import com.gituhub.chavesrodolfo.campaign.model.representations.CampaignRepresentation;

public interface CampaignService {

    CampaignRepresentation create(CampaignRepresentation campaignRepresentation) throws CampaignNotAcceptedException;

	/**
	 * Lista campanhas que NÃO estão com a data de vigência vencidas
	 * 
	 * @return List<CampaignRepresentation>
	 */
	List<CampaignRepresentation> listAllValid();

	CampaignRepresentation findById(Long id) throws CampaignNotFoundException;

	CampaignRepresentation update(Long id, CampaignRepresentation campaignRepresentation) throws CampaignNotFoundException;

	void delete(Long id) throws CampaignNotFoundException;

}
