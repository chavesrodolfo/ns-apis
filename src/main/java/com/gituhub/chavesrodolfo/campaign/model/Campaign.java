package com.gituhub.chavesrodolfo.campaign.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Campaign {
    
    public Campaign(CampaignRepresentation campaignRepresentation) {
        this.name = campaignRepresentation.getName();
        this.validity = campaignRepresentation.getValidity();
        this.heartClub = campaignRepresentation.getHeartClub();
    }
    
	@Id
	@GeneratedValue
    private Long id;
    
    private String name;
    private Date validity;
    private Long heartClub;

	public CampaignRepresentation transformToRepresentation() {
        CampaignRepresentation campaignRepresentation = new CampaignRepresentation();
        campaignRepresentation.setName(this.getName());
        campaignRepresentation.setHeartClub(this.getHeartClub());
        campaignRepresentation.setValidity(this.validity);
        campaignRepresentation.setId(this.getId());
		return campaignRepresentation;
    }
    
}
