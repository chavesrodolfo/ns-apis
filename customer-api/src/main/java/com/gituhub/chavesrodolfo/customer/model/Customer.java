package com.gituhub.chavesrodolfo.customer.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.gituhub.chavesrodolfo.customer.model.representations.CustomerRepresentation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {
    
	@Id
	@GeneratedValue
    private Long id;
    
    private String fullName;
    private String email;
    private Date dateOfBirth;
    private Long heartClub;
    private String campaigns;

    public Customer(CustomerRepresentation customerRepresentation) {
        this.id = customerRepresentation.getId();
        this.fullName = customerRepresentation.getFullName();
        this.email = customerRepresentation.getEmail();
        this.dateOfBirth = customerRepresentation.getDateOfBirth();
        this.heartClub = customerRepresentation.getHeartClub();
        this.campaigns = customerRepresentation.getCampaigns();
    }
    
	public CustomerRepresentation transformToRepresentation() {
        CustomerRepresentation customerRepresentation = new CustomerRepresentation();
        customerRepresentation.setFullName(this.fullName);
        customerRepresentation.setHeartClub(this.heartClub);
        customerRepresentation.setDateOfBirth(this.dateOfBirth);
        customerRepresentation.setEmail(this.email);
        customerRepresentation.setId(this.id);
        customerRepresentation.setCampaigns(this.campaigns);
		return customerRepresentation;
    }
    
}
