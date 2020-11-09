package com.gituhub.chavesrodolfo.customer.controller;

import java.util.List;

import com.gituhub.chavesrodolfo.customer.exceptions.CustomerNotAcceptedException;
import com.gituhub.chavesrodolfo.customer.model.representations.CustomerRepresentation;
import com.gituhub.chavesrodolfo.customer.model.representations.MessageResponse;
import com.gituhub.chavesrodolfo.customer.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/hello")
    public String hello() {
        return "Customer API";
    }

    @PostMapping("/customers")
    public MessageResponse createCustomer(@RequestBody CustomerRepresentation customerRepresentation) throws CustomerNotAcceptedException{
        return customerService.create(customerRepresentation);
    }

    @GetMapping("/customers")
    public List<CustomerRepresentation> listCustomers() {
        List<CustomerRepresentation> customers = customerService.listAllValid();
        return customers;
    }

    @GetMapping("/customers/{id}")
    public CustomerRepresentation findCustomer(@PathVariable("id") Long id) throws Exception {
        CustomerRepresentation customer = customerService.findById(id);
        return customer;
    }

    @PatchMapping("/customers/updatecampaigns")
    public CustomerRepresentation updateCustomerCampaigns(@RequestBody CustomerRepresentation customerRepresentation) throws Exception {
        return customerService.update(customerRepresentation);
    }
}
