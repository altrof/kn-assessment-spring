package com.altrof.store.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void createNewProduct(Product product) {
        Optional<Product> productOptional = productRepository.findProductByNameIgnoreCase(product.getName());

        if (productOptional.isPresent()) {
            throw new IllegalStateException("Product is already in product list.");
        }
        productRepository.save(product);
    }

}
