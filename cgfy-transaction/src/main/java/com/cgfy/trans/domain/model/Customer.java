package com.cgfy.trans.domain.model;

import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Entity(name = "customer")
@Data
public class Customer  implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_name")
    private String username;

    private String password;

    private String role;

}
