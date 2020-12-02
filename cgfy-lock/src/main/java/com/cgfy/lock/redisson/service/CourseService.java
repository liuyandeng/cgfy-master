package com.cgfy.lock.redisson.service;

import com.cgfy.lock.redisson.domain.model.CourseModel;

public interface CourseService {

    CourseModel getById(Integer courseId);

    void upload(Integer userId, Integer courseId, Integer studyProcess);
}
