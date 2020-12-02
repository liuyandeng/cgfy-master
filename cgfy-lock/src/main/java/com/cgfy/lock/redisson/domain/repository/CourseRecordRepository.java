package com.cgfy.lock.redisson.domain.repository;

import com.cgfy.lock.redisson.domain.model.CourseRecordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRecordRepository extends JpaSpecificationExecutor<CourseRecordModel>, JpaRepository<CourseRecordModel, Integer> {
}
