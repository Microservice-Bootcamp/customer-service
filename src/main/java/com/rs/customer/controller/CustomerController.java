package com.rs.customer.controller;

import com.rs.customer.entity.Customer;
import com.rs.customer.service.CustomerService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@Log4j2
@RestController
@RequestMapping("/user")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public Mono<Customer> saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @CircuitBreaker(name = "customerCB", fallbackMethod = "fallBackGetCustomer")
    @GetMapping("/{dni}")
    public Mono<Customer> findCustomerByDni(@PathVariable("dni") Integer numberDni){
        return customerService.findCustomerByDni(numberDni);
    }

    @GetMapping("/status/{id}")
    public Mono<Boolean> existCustomerById(@PathVariable("id") Integer dniNumber){
        log.info("jaaa");
        return customerService.existCustomerByDni(dniNumber);
    }


    @GetMapping("/person/{dniNumber}")
    public Mono<Boolean> isVipConsumer(@PathVariable("dniNumber") Integer dniNumber){
        return customerService.isVipCustomer(dniNumber);
    }


    @GetMapping("/business/{dniNumber}")
    public Mono<Boolean> isVipBusiness(@PathVariable("dniNumber") Integer dniNumber){
        return customerService.isVipBusiness(dniNumber);
    }

    private Mono<Customer> fallBackGetCustomer(@PathVariable("dni") Integer numberDni,Throwable t){
        log.error(t.getMessage() + " -- user with dni "+numberDni);
        Customer customer = new Customer();
        customer.setName("Sorry no customer information currently available");
        return Mono.just(customer);
    }

    /*private Mono<Boolean> fallbackBoolean(@PathVariable("id") Integer dniNumber,Exception e){
        log.error(e.getMessage() + " timeout for:"+dniNumber);
        return Mono.just(false);
    }*/


}
