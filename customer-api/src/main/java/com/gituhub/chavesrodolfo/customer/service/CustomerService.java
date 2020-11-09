package com.gituhub.chavesrodolfo.customer.service;

import java.util.List;

import com.gituhub.chavesrodolfo.customer.exceptions.CustomerCreatedException;
import com.gituhub.chavesrodolfo.customer.exceptions.CustomerNotAcceptedException;
import com.gituhub.chavesrodolfo.customer.exceptions.CustomerNotFoundException;
import com.gituhub.chavesrodolfo.customer.model.representations.CustomerRepresentation;
import com.gituhub.chavesrodolfo.customer.model.representations.MessageResponse;

public interface CustomerService {

	MessageResponse create(CustomerRepresentation customerRepresentation)
			throws CustomerNotAcceptedException, CustomerCreatedException;

	List<CustomerRepresentation> listAllValid();

	CustomerRepresentation findById(Long id) throws CustomerNotFoundException;

	CustomerRepresentation update(CustomerRepresentation customerRepresentation)
			throws CustomerNotFoundException;

}
