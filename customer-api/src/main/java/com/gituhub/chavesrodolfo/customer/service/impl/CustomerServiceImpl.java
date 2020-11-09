package com.gituhub.chavesrodolfo.customer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gituhub.chavesrodolfo.customer.exceptions.CustomerCreatedException;
import com.gituhub.chavesrodolfo.customer.exceptions.CustomerNotAcceptedException;
import com.gituhub.chavesrodolfo.customer.exceptions.CustomerNotFoundException;
import com.gituhub.chavesrodolfo.customer.model.Customer;
import com.gituhub.chavesrodolfo.customer.model.representations.CustomerRepresentation;
import com.gituhub.chavesrodolfo.customer.model.representations.MessageResponse;
import com.gituhub.chavesrodolfo.customer.repository.CustomerRepository;
import com.gituhub.chavesrodolfo.customer.service.CampaignService;
import com.gituhub.chavesrodolfo.customer.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CampaignService campaignService;

    @Override
    public MessageResponse create(CustomerRepresentation customerRepresentation)
            throws CustomerNotAcceptedException, CustomerCreatedException {
        log.info("Creating Customer {}", customerRepresentation.getFullName());

        verifyCustomerData(customerRepresentation);

        Optional<Customer> optionalCustomer = customerRepository.findCustomerByEmail(customerRepresentation.getEmail());

        // O Cliente não pode ter mais de um cadastro ativo
        if (optionalCustomer.isPresent()) {
            // Dado um E-mail que já existe, caso o cliente não tenha nenhuma campanha
            // associada, o serviço deverá enviar
            // as novas campanhas como resposta;
            if (StringUtils.isEmpty(optionalCustomer.get().getCampaigns())) {
                
                //default campaign for Fallback
                String campaigns = "1";

                try {
                    campaigns = campaignService.fetchCampaignsFromAPI(customerRepresentation.getHeartClub());
                } catch (Exception e) {
                    log.error("Error to fetch from Campaigns API.", e);
                }

                customerRepresentation = new CustomerRepresentation();
                customerRepresentation.setCampaigns(campaigns);

                return new MessageResponse(HttpStatus.NOT_ACCEPTABLE.name(),
                        String.format("Customer already created. New Campaigns to participate: %s", campaigns));
            } else {
                throw new CustomerCreatedException();
            }

        }

        Customer customer = new Customer(customerRepresentation);
        customer = customerRepository.save(customer);
        return new MessageResponse(HttpStatus.CREATED.name(), "Customer created");
    }

    private void verifyCustomerData(CustomerRepresentation customerRepresentation) {
        if (customerRepresentation.getFullName() == null || customerRepresentation.getEmail() == null
                || customerRepresentation.getDateOfBirth() == null || customerRepresentation.getHeartClub() == null) {
            throw new CustomerNotAcceptedException();
        }
    }

    @Override
    public List<CustomerRepresentation> listAllValid() {

        log.info("Listing all valid Customers");

        List<Customer> customers = customerRepository.findAll();
        List<CustomerRepresentation> customersRep = new ArrayList<CustomerRepresentation>();

        for (Customer customer : customers) {
            CustomerRepresentation customerRepresentation = customer.transformToRepresentation();
            customersRep.add(customerRepresentation);
        }

        return customersRep;
    }

    @Override
    public CustomerRepresentation findById(Long id) throws CustomerNotFoundException {
        log.info("Listing Customer id {}", id);

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        return customer.transformToRepresentation();
    }

    @Override
    public CustomerRepresentation update(CustomerRepresentation customerRep) throws CustomerNotFoundException {
        log.info("Updating Customer {} with campaigns {}", customerRep.getFullName(), customerRep.getCampaigns());

        Customer customer = customerRepository.findById(customerRep.getId()).orElseThrow(() -> new CustomerNotFoundException(customerRep.getId()));
        customer.setCampaigns(customerRep.getCampaigns());
        customer = customerRepository.save(customer);

        return customer.transformToRepresentation();
    }

}
