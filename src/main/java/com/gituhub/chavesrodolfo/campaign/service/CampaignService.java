package com.gituhub.chavesrodolfo.campaign.service;

import java.util.List;

import com.gituhub.chavesrodolfo.campaign.model.CampaignRepresentation;

public interface CampaignService {

    CampaignRepresentation create(CampaignRepresentation campaignRepresentation);

	/**
	 * Lista campanhas que NÃO estão com a data de vigência vencidas
	 * 
	 * @return List<CampaignRepresentation>
	 */
	List<CampaignRepresentation> listAllValid();

	CampaignRepresentation findById(Long id) throws Exception;

	CampaignRepresentation update(Long id, CampaignRepresentation campaignRepresentation) throws Exception;

	void delete(Long id) throws Exception;

}
