package com.altrof.store.customer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;


@DataJpaTest()
@DisplayName("customer repository test")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        customerRepositoryUnderTest.deleteAll();
    }

    @Test
    @DisplayName("Test #1 Trying to find a customer with CORRECT should return TRUE")
    void itShouldFindCustomerByEmail() {
        Customer customer = new Customer();
        customer.setCustomerId(UUID.randomUUID());
        customer.setFullName("Test Tester");
        customer.setEmail("tester@test.pro");
        customer.setPhoneNumber(123456789L);
        customerRepositoryUnderTest.save(customer);

        Optional<Customer> customerOptional = customerRepositoryUnderTest.findCustomerByEmail("tester@test.pro");

        assertThat(customerOptional.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Test #2 : Trying to find a customer with INCORRECT should return FALSE")
    void itShouldNotFindCustomerByEmail() {
        Customer customer = new Customer();
        customer.setCustomerId(UUID.randomUUID());
        customer.setFullName("Test Tester");
        customer.setEmail("tester@test.pro");
        customer.setPhoneNumber(123456789L);
        customerRepositoryUnderTest.save(customer);

        Optional<Customer> customerOptional = customerRepositoryUnderTest.findCustomerByEmail("asd@qwerty.com");

        assertThat(customerOptional.isPresent()).isFalse();
    }
}