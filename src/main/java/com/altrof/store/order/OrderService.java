package com.altrof.store.order;

import com.altrof.store.customer.Customer;
import com.altrof.store.customer.CustomerRepository;
import com.altrof.store.exception.ApiRequestException;
import com.altrof.store.product.Product;
import com.altrof.store.product.ProductRepository;
import com.altrof.store.validators.UUIDValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Data
@AllArgsConstructor
@Service
public class OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private final UUIDValidator uuidValidator;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(String orderId) {
        if(orderRepository.findById(UUID.fromString(orderId)).isPresent()) {
            return orderRepository.findById(UUID.fromString(orderId)).get();
        }
        return null;
    }

    public List<Order> getOrdersByDate(String date){
        return orderRepository.findAllByDate(LocalDate.parse(date)).stream().toList();
    }

    public List<Order> getOrdersByProduct(String productId){
        return orderRepository.findByOrderLines_Product_Id(UUID.fromString(productId)).stream().toList();
    }


    public List<Order> getOrdersByCustomer(String customerId){
        Optional<Customer> customerOptional = customerRepository.findById(UUID.fromString(customerId));
        if(customerOptional.isEmpty()) {
           throw new IllegalStateException("No customer with this id");
        }
        return orderRepository.findByCustomer_CustomerId(UUID.fromString(customerId)).stream().toList();
    }

    public void createNewOrder(Map<String, Object> payload) {

        if(payload == null || !payload.containsKey("customerId")
                || !payload.containsKey("productId")
                || !payload.containsKey("quantity")) {
            throw new ApiRequestException("Not all arguments passed..");

        } else {

            UUID customerId =  UUID.fromString(payload.get("customerId").toString());
            UUID productId = UUID.fromString(payload.get("productId").toString());

            Customer customer = customerRepository.getById(customerId);
            Product product = productRepository.getById(productId);
            Order order = new Order(customer);
            if (orderRepository.findOrderByCustomer(customer).isPresent()) {
                order = orderRepository.findOrderByCustomer(customer).get();
            }
            OrderLine orderLine = new OrderLine(product, Integer.parseInt(payload.get("quantity").toString()));
            order.getOrderLines().add(orderLine);
            orderRepository.save(order);
        }

    }

    public void setOrderLineQuantity(Map<String, Object> payload) {

        if (payload == null || !payload.containsKey("orderLineId") || !payload.containsKey("quantity")) {
            throw new ApiRequestException("Not all arguments passed..");
        }

        if (!uuidValidator.test(payload.get("orderLineId").toString())) {
            throw new ApiRequestException("uuid is not valid");
        }

        if (Integer.parseInt(payload.get("quantity").toString()) <= 0) {
            throw new ApiRequestException("quantity cannot be zero or less than zero.");
        }

        UUID orderLineId = UUID.fromString(payload.get("orderLineId").toString());
        Optional<OrderLine> orderLine = orderRepository.findOrderLineById(orderLineId);
        if (orderLine.isEmpty()) {
            throw new ApiRequestException("Order does not have line with this uuid");
        } else {
            Optional<Order> order = orderRepository.findOrderByOrderLine(orderLine.get());
            orderLine.get()
                    .setProductQuantity(
                            Integer.parseInt(payload.get("quantity").toString()));
            orderRepository.save(order.get());
        }

    }
}
