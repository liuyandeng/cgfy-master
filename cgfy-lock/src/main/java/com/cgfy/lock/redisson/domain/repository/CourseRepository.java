package com.cgfy.lock.redisson.domain.repository;

import com.cgfy.lock.redisson.domain.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRepository extends JpaSpecificationExecutor<CourseModel>, JpaRepository<CourseModel, Integer> {
}
