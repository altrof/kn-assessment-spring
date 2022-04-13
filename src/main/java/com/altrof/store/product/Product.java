package com.altrof.store.product;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Data
@Entity(name = "Products")
@Table(name = "products", schema = "public")
@RequiredArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id")
    private UUID id = UUID.randomUUID();
    @Column(nullable = false, name = "product_name")
    private String name;
    @Column(nullable = false, name = "product_price")
    private int price;

}
