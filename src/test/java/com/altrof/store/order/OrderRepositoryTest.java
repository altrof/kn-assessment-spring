package com.altrof.store.order;

import com.altrof.store.customer.Customer;
import com.altrof.store.customer.CustomerRepository;
import com.altrof.store.product.Product;
import com.altrof.store.product.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest()
@DisplayName("order repository test")
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepositoryUnderTest;

    @Autowired
    private CustomerRepository customerRepositoryUnderTest;

    @Autowired
    private ProductRepository productRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        orderRepositoryUnderTest.deleteAll();
    }

    @Test
    void itShouldFindAllOrderByCustomerIdAndReturnTrue() {
        Customer customer = new Customer();
        customer.setCustomerId(UUID.randomUUID());
        customer.setFullName("Test Tester");
        customer.setEmail("tester@test.pro");
        customer.setPhoneNumber(123456789L);
        customerRepositoryUnderTest.save(customer);

        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Test product");
        product.setPrice(150);
        productRepositoryUnderTest.save(product);

        Order order = new Order(customer);
        order.getOrderLines().add(new OrderLine(product, 2));

        orderRepositoryUnderTest.save(order);
        List<Order> orderOptional = orderRepositoryUnderTest
                                    .findByCustomer_CustomerId(customer.getCustomerId());


        assertThat(orderOptional.size()).isEqualTo(1);
    }


    @Test
    @DisplayName("Passed 2 obj")
    void itShouldFindAllOrdersByGivenDateAndReturnTrue() {
        Customer customer1 = new Customer();
        customer1.setCustomerId(UUID.randomUUID());
        customer1.setFullName("Tester One");
        customer1.setEmail("testerone@test.pro");
        customer1.setPhoneNumber(123456789L);
        customerRepositoryUnderTest.save(customer1);

        Customer customer2 = new Customer();
        customer2.setCustomerId(UUID.randomUUID());
        customer2.setFullName("Tester Two");
        customer2.setEmail("testertwo@test.pro");
        customer2.setPhoneNumber(987654321L);
        customerRepositoryUnderTest.save(customer2);

        Order order1 = new Order(customer1);
        Order order2 = new Order(customer2);
        orderRepositoryUnderTest.save(order1);
        orderRepositoryUnderTest.save(order2);

        List<Order> orderOptional = orderRepositoryUnderTest
                .findAllByDate(LocalDate.now());


        assertThat(orderOptional.size()).isEqualTo(2);
    }
}


