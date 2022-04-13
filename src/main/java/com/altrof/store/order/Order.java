package com.altrof.store.order;

import com.altrof.store.customer.Customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name = "purchase_order")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @Column(name = "order_id")
    private UUID id = UUID.randomUUID();

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @OneToMany(targetEntity = OrderLine.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_order_id", referencedColumnName = "order_id")
    private Set<OrderLine> orderLines = new HashSet<>();

    @OneToOne(targetEntity = Customer.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer", referencedColumnName = "customer_id")
    private Customer customer;

    public Order(Customer customer) {
        this.customer = customer;
    }

}
