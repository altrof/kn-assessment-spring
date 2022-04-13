package com.altrof.store.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    //@Query("SELECT customer FROM Customer customer WHERE customer.email = ?1")
    Optional<Customer> findCustomerByEmail(String email);
}
