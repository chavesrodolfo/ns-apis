package com.gituhub.chavesrodolfo.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import com.gituhub.chavesrodolfo.customer.controller.CustomerController;
import com.gituhub.chavesrodolfo.customer.model.representations.CustomerRepresentation;
import com.gituhub.chavesrodolfo.customer.model.representations.MessageResponse;

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
class CustomerApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CustomerController customerController;

	@Test
	void testHello() throws RestClientException, MalformedURLException {
		ResponseEntity<String> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/api/hello").toString(), String.class);
		assertEquals("Customer API", response.getBody());
	}

	@Test
	public void testCreateCustomerAndAssociateCampaigns() throws Exception {

		CustomerRepresentation customerRep = new CustomerRepresentation();
		customerRep.setDateOfBirth(new Date());
		customerRep.setHeartClub(1L);
		customerRep.setEmail("rossgl@gmail.com");
		customerRep.setFullName("Ross Geller");

		MessageResponse messageResponse = customerController.createCustomer(customerRep);
		assertThat(messageResponse.getStatus()).isEqualToIgnoringCase("CREATED");

		customerRep.setCampaigns("1,2,3");
		customerRep.setId(1L);
		CustomerRepresentation customerRepresentation = customerController.updateCustomerCampaigns(customerRep);
		assertThat(customerRepresentation.getCampaigns()).isEqualToIgnoringCase("1,2,3");
	}

	@Test
	public void testCreateCustomer() throws Exception {

		CustomerRepresentation customerRep = new CustomerRepresentation();
		customerRep.setDateOfBirth(new Date());
		customerRep.setHeartClub(1L);
		customerRep.setEmail("joey@gmail.com");
		customerRep.setFullName("Joey Tribianni");

		MessageResponse messageResponse = customerController.createCustomer(customerRep);

		assertThat(messageResponse.getStatus()).isEqualToIgnoringCase("CREATED");
	}

	@Test
	public void testCreateCustomerWithCampaigns() throws Exception {

		CustomerRepresentation customerRep = new CustomerRepresentation();
		customerRep.setDateOfBirth(new Date());
		customerRep.setHeartClub(1L);
		customerRep.setEmail("monica@gmail.com");
		customerRep.setFullName("Monica Geller");
		customerRep.setCampaigns("1,2,3");

		MessageResponse messageResponse = customerController.createCustomer(customerRep);

		assertThat(messageResponse.getStatus()).isEqualToIgnoringCase("CREATED");
	}
}
