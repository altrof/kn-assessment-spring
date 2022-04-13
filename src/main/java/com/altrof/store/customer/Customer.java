package com.altrof.store.customer;


import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.UUID;


@Data
@EqualsAndHashCode
@Entity(name = "Customer")
@Table(name = "customers")
@RequiredArgsConstructor
public class Customer {
    @Id
    @Column(name = "customer_id")
    private UUID customerId = UUID.randomUUID(); // registration code

    @Column(nullable = false, name = "full_name")
    private String fullName;
    @Column(nullable = false, name = "email")
    private String email;
    @Column(nullable = false, name = "phone_number")
    private Long phoneNumber;


}
