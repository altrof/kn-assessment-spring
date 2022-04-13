package com.altrof.store.customer;

import com.altrof.store.exception.ApiRequestException;
import com.altrof.store.validators.EmailValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private final EmailValidator emailValidator;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void createNewCustomer(@NonNull Customer customer) {
        if(customer.getFullName() == null
                || customer.getPhoneNumber() == null || customer.getEmail() == null ) {
            throw new ApiRequestException("Not all arguments passed..");
        }
        if(!emailValidator.test(customer.getEmail())) {
            throw new ApiRequestException(customer.getEmail() + " is not valid");
        }
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("Customer is already exist with this email.");
        }
        customerRepository.save(customer);
    }
}
