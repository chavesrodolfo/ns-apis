package com.gituhub.chavesrodolfo.campaign;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.gituhub.chavesrodolfo.campaign.controller.CampaignController;
import com.gituhub.chavesrodolfo.campaign.model.representations.CampaignRepresentation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class CampaignApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CampaignController campaignController;

	@Test
	void testHello() throws RestClientException, MalformedURLException {
		ResponseEntity<String> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/api/hello").toString(), String.class);
		assertEquals("Campaigns API", response.getBody());
	}

	@Test
	public void testCreateCampaign() throws Exception {

		CampaignRepresentation campaignRepresentation = new CampaignRepresentation();
		campaignRepresentation.setName("Campanha 1");
		campaignRepresentation.setHeartClub(1L);
		campaignRepresentation.setStartDate(Date
				.from(LocalDateTime.of(2021, Month.OCTOBER, 1, 00, 00, 00).atZone(ZoneId.systemDefault()).toInstant()));
		campaignRepresentation.setEndDate(Date
				.from(LocalDateTime.of(2021, Month.OCTOBER, 3, 00, 00, 00).atZone(ZoneId.systemDefault()).toInstant()));
		campaignRepresentation = campaignController.createCampaign(campaignRepresentation);

		assertThat(campaignRepresentation.getName()).isEqualToIgnoringCase("Campanha 1");
	}

	@Test
	public void testCreateInvalidCampaign() throws Exception {

		CampaignRepresentation firstCampaign = new CampaignRepresentation();
		firstCampaign.setName("Campanha 1");
		firstCampaign.setHeartClub(1L);
		firstCampaign.setStartDate(Date
				.from(LocalDateTime.of(2021, Month.OCTOBER, 1, 00, 00, 00).atZone(ZoneId.systemDefault()).toInstant()));
		firstCampaign.setEndDate(Date
				.from(LocalDateTime.of(2021, Month.OCTOBER, 3, 00, 00, 00).atZone(ZoneId.systemDefault()).toInstant()));

		campaignController.createCampaign(firstCampaign);

		CampaignRepresentation secondCampaign = new CampaignRepresentation();
		secondCampaign.setName("Campanha 2");
		secondCampaign.setHeartClub(1L);
		secondCampaign.setStartDate(Date
				.from(LocalDateTime.of(2021, Month.OCTOBER, 1, 00, 00, 00).atZone(ZoneId.systemDefault()).toInstant()));
		secondCampaign.setEndDate(Date
				.from(LocalDateTime.of(2021, Month.OCTOBER, 2, 00, 00, 00).atZone(ZoneId.systemDefault()).toInstant()));

		campaignController.createCampaign(secondCampaign);

		CampaignRepresentation thridCampaign = new CampaignRepresentation();
		thridCampaign.setName("Campanha 3");
		thridCampaign.setHeartClub(1L);
		thridCampaign.setStartDate(Date
				.from(LocalDateTime.of(2021, Month.OCTOBER, 1, 00, 00, 00).atZone(ZoneId.systemDefault()).toInstant()));
		thridCampaign.setEndDate(Date
				.from(LocalDateTime.of(2021, Month.OCTOBER, 2, 00, 00, 00).atZone(ZoneId.systemDefault()).toInstant()));

		campaignController.createCampaign(thridCampaign);

		List<CampaignRepresentation> campaigns = campaignController.listCampaigns();

		assertThat(campaigns.get(0).getEndDate()).hasSameTimeAs(Date
				.from(LocalDateTime.of(2021, Month.OCTOBER, 5, 00, 00, 00).atZone(ZoneId.systemDefault()).toInstant()));

		assertThat(campaigns.get(1).getEndDate()).hasSameTimeAs(Date
				.from(LocalDateTime.of(2021, Month.OCTOBER, 2, 00, 00, 00).atZone(ZoneId.systemDefault()).toInstant()));

		assertThat(campaigns.get(2).getEndDate()).hasSameTimeAs(Date
				.from(LocalDateTime.of(2021, Month.OCTOBER, 3, 00, 00, 00).atZone(ZoneId.systemDefault()).toInstant()));
	}

}
