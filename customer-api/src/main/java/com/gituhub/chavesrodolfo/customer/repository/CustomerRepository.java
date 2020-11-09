package com.gituhub.chavesrodolfo.customer.repository;

import java.util.Optional;

import com.gituhub.chavesrodolfo.customer.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
	Optional<Customer> findCustomerByEmail(String email);

}
