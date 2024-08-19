package com.BookManager.demo.Controller;

import com.BookManager.demo.model.Customers;
import com.BookManager.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/Customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping //
    public List<Customers> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customers> getCustomerById(@PathVariable Long id) {
        Optional<Customers> customersOptional = customerService.getCustomerById(id);
        return customersOptional
                .map(x -> new ResponseEntity<>(x , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Customers createCustomer(@RequestBody Customers customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable Long id,
                                           @RequestBody Customers customer) {
        Optional<Customers> customersOptional = customerService.getCustomerById(id);
        return customersOptional
                .map(a -> new ResponseEntity<>(a , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<Customers> customersOptional = customerService.getCustomerById(id);
        if(customersOptional.isPresent()){
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
