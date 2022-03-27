package com.rs.customer.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer")
public class Customer {
    @Id
    private String idCustomer;

    private String name;
    private String lastName;
    private Integer dni;
    private String typeCustomer;
    private Boolean vip;

}
