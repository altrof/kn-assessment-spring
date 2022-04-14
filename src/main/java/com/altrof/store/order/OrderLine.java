package com.altrof.store.order;

import com.altrof.store.product.Product;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.UUID;

@JsonSerialize
@Entity(name = "OrderLine")
@Table(name = "order_line")
@Data
@NoArgsConstructor
public class OrderLine {
    @Id
    @Column(name = "order_line_id")
    private UUID orderLineId = UUID.randomUUID();

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int productQuantity;

    public OrderLine(Product product, int productQuantity) {
        this.product = product;
        this.productQuantity = productQuantity;
    }
}
