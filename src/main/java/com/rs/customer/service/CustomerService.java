package com.rs.customer.service;

import com.rs.customer.entity.Customer;
import com.rs.customer.repository.CustomerRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Log4j2
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Mono<Customer> saveCustomer(Customer customer){
        if(typeCustomer.test(customer.getTypeCustomer())){
            return customerRepository.existsByDni(customer.getDni())
                    .filter(value->value.equals(false))
                    .flatMap(mm->customerRepository.save(customer));
        }
        return Mono.empty();
    }


    public Mono<Customer> findCustomerByDni(Integer numberDni){
        return customerRepository.findByDni(numberDni);

    }
    Predicate<String> typeCustomer = type-> type.equals("personal")||type.equals("business");

    public Mono<Boolean> existCustomerById(String customerId){
        return customerRepository.existsById(customerId);
    }

    public Mono<Boolean> isVipCustomer(Integer dniNumber){
        return customerRepository.findByDni(dniNumber)
                .flatMap(person->validateVipCustomer(person, "personal"));
    }

    public Mono<Boolean> isVipBusiness(Integer dniNumber){
        return customerRepository.findByDni(dniNumber)
                .flatMap(person->validateVipCustomer(person, "business"));
    }


    private Mono<Boolean> validateVipCustomer(Customer customer, String typeCustomer){
        if(customer.getTypeCustomer().equals(typeCustomer)&&customer.getVip().equals(true)){
            return Mono.just(true);
        }
        return Mono.just(false);
    }




}
