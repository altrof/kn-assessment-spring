package com.altrof.store.product;

import com.altrof.store.customer.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest()
@DisplayName("customer repository test")
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        productRepositoryUnderTest.deleteAll();
    }

    @Test
    @DisplayName("Test #1: Should return TRUE if PRODUCT is present by name")
    void itShouldFindProductByNameWithIgnoreCaseAndReturnTrue() {
        Product product = new Product();
        product.setName("Test product");
        product.setPrice(150);

        productRepositoryUnderTest.save(product);
        Optional<Product> productOptional = productRepositoryUnderTest
                                            .findProductByNameIgnoreCase("tEsT PrOdUcT");

        assertThat(productOptional.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Test #2: Should return FALSE if PRODUCT is not present by name")
    void itShouldFindProductByNameWithIgnoreCaseAndReturnFalse() {
        Product product = new Product();
        product.setName("Test product");
        product.setPrice(150);

        productRepositoryUnderTest.save(product);
        Optional<Product> productOptional = productRepositoryUnderTest
                .findProductByNameIgnoreCase("Product test");

        assertThat(productOptional.isPresent()).isFalse();
    }
}