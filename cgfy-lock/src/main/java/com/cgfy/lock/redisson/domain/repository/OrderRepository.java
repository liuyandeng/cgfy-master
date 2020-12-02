package com.cgfy.lock.redisson.domain.repository;


import com.cgfy.lock.redisson.domain.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaSpecificationExecutor<OrderModel>, JpaRepository<OrderModel, Integer> {
}
