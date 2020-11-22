package com.cgfy.trans.domain.repository;

import com.cgfy.trans.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
