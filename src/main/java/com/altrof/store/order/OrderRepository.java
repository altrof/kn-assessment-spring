package com.altrof.store.order;

import com.altrof.store.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findOrderByCustomer(Customer customer);

    List<Order> findAllByDate(LocalDate date);

    @Query("SELECT o FROM Order o JOIN o.customer c WHERE c.customerId = :customerId")
    List<Order> findByCustomer_CustomerId(UUID customerId);

    @Query("SELECT o FROM Order o JOIN o.orderLines ol JOIN ol.product p WHERE p.id = :productId")
    Optional<Order> findByOrderLines_Product_Id(UUID productId);

    @Query("SELECT ol FROM Order o JOIN o.orderLines ol WHERE o.customer.customerId = :customerId AND o.id = :orderId AND ol.product.id = :productId")
    Optional<OrderLine> findOrderLineByOrderIdAndCustomerIdAndProductId(UUID orderId, UUID customerId, UUID productId);

    @Query("SELECT o FROM Order o JOIN o.orderLines ol WHERE ol = :orderLine")
    Optional<Order> findOrderByOrderLine(OrderLine orderLine);
}