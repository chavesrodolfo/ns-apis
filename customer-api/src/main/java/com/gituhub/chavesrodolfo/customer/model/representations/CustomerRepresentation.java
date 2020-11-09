package com.gituhub.chavesrodolfo.customer.model.representations;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRepresentation {
    private Long id;
    private String fullName;
    private String email;
    private Date dateOfBirth;
    private Long heartClub;
    private String campaigns;
}
