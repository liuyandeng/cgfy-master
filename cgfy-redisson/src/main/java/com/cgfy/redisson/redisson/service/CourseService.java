package com.cgfy.redisson.redisson.service;

import com.cgfy.redisson.Redisson.domain.model.CourseModel;

public interface CourseService {

    CourseModel getById(Integer courseId);

    void upload(Integer userId, Integer courseId, Integer studyProcess);
}
