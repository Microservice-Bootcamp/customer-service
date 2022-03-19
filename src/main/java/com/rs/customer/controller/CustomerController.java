package com.rs.customer.controller;

import com.rs.customer.entity.Customer;
import com.rs.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public Mono<Customer> saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{dni}")
    public Mono<Customer> findCustomerByDni(@PathVariable("dni") Integer numberDni){
        return customerService.findCustomerByDni(numberDni);
    }

    @GetMapping("/status/{id}")
    public Mono<Boolean> existCustomerById(@PathVariable("id") String customerId){
        return customerService.existCustomerById(customerId);
    }

    @GetMapping("/person/{dniNumber}")
    public Mono<Boolean> isVipConsumer(@PathVariable("dniNumber") Integer dniNumber){
        return customerService.isVipCustomer(dniNumber);
    }
    @GetMapping("/business/{dniNumber}")
    public Mono<Boolean> isVipBusiness(@PathVariable("dniNumber") Integer dniNumber){
        return customerService.isVipBusiness(dniNumber);
    }


}
