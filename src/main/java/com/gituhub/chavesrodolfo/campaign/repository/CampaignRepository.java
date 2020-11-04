package com.gituhub.chavesrodolfo.campaign.repository;

import java.util.List;

import com.gituhub.chavesrodolfo.campaign.model.Campaign;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @Query("select c from Campaign c where c.validity >= CURRENT_DATE")
	List<Campaign> findAllValidCampaigns();

}
