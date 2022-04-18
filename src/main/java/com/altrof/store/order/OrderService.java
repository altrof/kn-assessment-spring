package com.altrof.store.order;

import com.altrof.store.customer.Customer;
import com.altrof.store.customer.CustomerRepository;
import com.altrof.store.exception.ApiRequestException;
import com.altrof.store.product.Product;
import com.altrof.store.product.ProductRepository;
import com.altrof.store.validators.DateValidator;
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
    private final DateValidator dateValidator;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(String orderId) {
        if(orderRepository.findById(UUID.fromString(orderId)).isEmpty()) {
            throw new ApiRequestException("No order with this id.");
        }
        return orderRepository.findById(UUID.fromString(orderId)).get();
    }

    public List<Order> getOrdersByDate(String date) {
        if(!dateValidator.test(date)) {
            throw new ApiRequestException("This date format is not support. Please type date in format yyyy-MM-dd");
        }
        return orderRepository.findAllByDate(LocalDate.parse(date)).stream().toList();
    }

    public List<Order> getOrdersByProduct(String productId) {
        return orderRepository.findByOrderLines_Product_Id(UUID.fromString(productId)).stream().toList();
    }


    public List<Order> getOrdersByCustomer(String customerId) {
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
            int quantityProps = Integer.parseInt(payload.get("quantity").toString());

            Customer customer = customerRepository.getById(customerId);
            Product product = productRepository.getById(productId);
            Order order = new Order(customer);
            if (orderRepository.findOrderByCustomer(customer).isPresent()) {
                order = orderRepository.findOrderByCustomer(customer).get();
            }

            OrderLine orderLine;
            // If order line is already exist with these orderId, customerId, productId,
            // then just increment to passed value
            if (orderRepository.findOrderLineByOrderIdAndCustomerIdAndProductId(
                    order.getId(), customerId, productId).isEmpty()) {

                orderLine = new OrderLine(product, quantityProps);
                order.getOrderLines().add(orderLine);
            } else {
                orderLine = orderRepository.findOrderLineByOrderIdAndCustomerIdAndProductId(
                        order.getId(), customerId, productId).get();
                orderLine.setProductQuantity(orderLine.getProductQuantity() + quantityProps);
            }

            orderRepository.save(order);
        }

    }

    public void updateOrderLineProductQuantity(Map<String, Object> payload) {



        if (payload == null || !payload.containsKey("customerId") || !payload.containsKey("productId")
                || !payload.containsKey("quantity") || !payload.containsKey("orderId")) {
            throw new ApiRequestException("Not all arguments passed..");
        }

        if (!uuidValidator.test(payload.get("customerId").toString())) {
            throw new ApiRequestException("UUID of customer is not valid");
        }

        if (!uuidValidator.test(payload.get("productId").toString())) {
            throw new ApiRequestException("UUID of product is not valid");
        }

        if (!uuidValidator.test(payload.get("orderId").toString())) {
            throw new ApiRequestException("UUID of order is not valid");
        }

        if (Integer.parseInt(payload.get("quantity").toString()) <= 0) {
            throw new ApiRequestException("quantity cannot be zero or less than zero.");
        }

        UUID orderId = UUID.fromString(payload.get("orderId").toString());
        UUID customerId = UUID.fromString(payload.get("customerId").toString());
        UUID productId = UUID.fromString(payload.get("productId").toString());

        Optional<OrderLine> orderLine = orderRepository
                .findOrderLineByOrderIdAndCustomerIdAndProductId(orderId, customerId, productId);
        if (orderLine.isEmpty()) {
            throw new ApiRequestException("Order does not have line with this uuid");
        } else {
            Optional<Order> order = orderRepository.findOrderByOrderLine(orderLine.get());
            orderLine.get().setProductQuantity(
                            Integer.parseInt(payload.get("quantity").toString()));
            order.ifPresent(value -> orderRepository.save(value));
        }

    }
}
