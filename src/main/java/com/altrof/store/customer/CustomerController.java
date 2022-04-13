package com.altrof.store.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }


    @PostMapping("/create")
    public void createNewCustomer(@RequestBody Customer customer) {
        customerService.createNewCustomer(customer);
    }
}

