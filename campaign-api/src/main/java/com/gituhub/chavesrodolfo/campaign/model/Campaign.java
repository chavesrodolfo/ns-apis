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
    
	@Id
	@GeneratedValue
    private Long id;
    
    private String name;
    private Date startDate;
    private Date endDate;
    private Long heartClub;

    public Campaign(CampaignRepresentation campaignRepresentation) {
        this.id = campaignRepresentation.getId();
        this.name = campaignRepresentation.getName();
        this.startDate = campaignRepresentation.getStartDate();
        this.endDate = campaignRepresentation.getEndDate();
        this.heartClub = campaignRepresentation.getHeartClub();
    }

	public CampaignRepresentation transformToRepresentation() {
        CampaignRepresentation campaignRepresentation = new CampaignRepresentation();
        campaignRepresentation.setName(this.name);
        campaignRepresentation.setHeartClub(this.heartClub);
        campaignRepresentation.setStartDate(this.startDate);
        campaignRepresentation.setEndDate(this.endDate);
        campaignRepresentation.setId(this.id);
		return campaignRepresentation;
    }
    
}
