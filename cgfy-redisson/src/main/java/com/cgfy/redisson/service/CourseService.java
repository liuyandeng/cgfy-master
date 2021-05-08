package com.cgfy.redisson.service;


import com.cgfy.redisson.domain.model.CourseModel;

public interface CourseService {

    CourseModel getById(Integer courseId);

    void upload(Integer userId, Integer courseId, Integer studyProcess);
}
