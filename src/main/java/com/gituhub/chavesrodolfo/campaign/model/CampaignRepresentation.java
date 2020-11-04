package com.gituhub.chavesrodolfo.campaign.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignRepresentation {
    private Long id;
    private String name;
    private Date validity;
    private Long heartClub;
}
