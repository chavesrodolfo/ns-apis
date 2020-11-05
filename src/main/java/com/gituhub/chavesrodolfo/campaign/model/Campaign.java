package com.gituhub.chavesrodolfo.campaign.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.gituhub.chavesrodolfo.campaign.model.representations.CampaignRepresentation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Campaign {
    
    public Campaign(CampaignRepresentation campaignRepresentation) {
        this.id = campaignRepresentation.getId();
        this.name = campaignRepresentation.getName();
        this.startDate = campaignRepresentation.getStartDate();
        this.endDate = campaignRepresentation.getEndDate();
        this.heartClub = campaignRepresentation.getHeartClub();
    }
    
	@Id
	@GeneratedValue
    private Long id;
    
    private String name;
    private Date startDate;
    private Date endDate;
    private Long heartClub;

	public CampaignRepresentation transformToRepresentation() {
        CampaignRepresentation campaignRepresentation = new CampaignRepresentation();
        campaignRepresentation.setName(this.getName());
        campaignRepresentation.setHeartClub(this.getHeartClub());
        campaignRepresentation.setStartDate(this.startDate);
        campaignRepresentation.setEndDate(this.endDate);
        campaignRepresentation.setId(this.getId());
		return campaignRepresentation;
    }
    
}
