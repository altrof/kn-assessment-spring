package com.altrof.store.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/orders")
public class OrderController {

    private OrderService orderService;

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/search-by-date/{date}")
    public List<Order> getOrdersByDate(@PathVariable String date) {
        return orderService.getOrdersByDate(date);
    }

    @GetMapping("/search-by-product/{productId}")
    public List<Order> getOrdersByProduct(@PathVariable String productId) {
        return orderService.getOrdersByProduct(productId);
    }

    @GetMapping("/search-by-customer/{customerId}")
    public List<Order> getOrdersByCustomer(@PathVariable String customerId) {
        return orderService.getOrdersByCustomer(customerId);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable String orderId) {
        return orderService.getOrder(orderId);
    }


    @PostMapping("/create")
    public void createNewOrder(@RequestBody Map<String, Object> payload) {
        orderService.createNewOrder(payload);
    }


    @PutMapping("/change-item-quantity")
    public void updateOrderLineProductQuantity(@RequestBody Map<String, Object> payload) {
        orderService.updateOrderLineProductQuantity(payload);
    }

}
