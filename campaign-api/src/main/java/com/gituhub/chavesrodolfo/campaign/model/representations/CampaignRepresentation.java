package com.gituhub.chavesrodolfo.campaign.model.representations;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignRepresentation {
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Long heartClub;
}
