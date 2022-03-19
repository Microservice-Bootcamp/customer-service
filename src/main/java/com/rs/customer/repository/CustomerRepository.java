package com.rs.customer.repository;

import com.rs.customer.entity.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

    Mono<Customer> findByDni(Integer numberDni);
    Mono<Boolean> existsByDni(Integer numberDni);


}
