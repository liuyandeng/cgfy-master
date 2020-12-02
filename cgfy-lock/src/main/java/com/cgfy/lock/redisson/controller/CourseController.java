package com.cgfy.lock.redisson.controller;

import com.alibaba.fastjson.JSON;

import com.cgfy.lock.redisson.domain.model.CourseModel;
import com.cgfy.lock.redisson.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 课程详情
     */
    @GetMapping("/info/{courseId}")
    public String info(@PathVariable("courseId") Integer courseId) {
        CourseModel model = courseService.getById(courseId);
        return JSON.toJSONString(model);
    }

    /**
     * 上报进度
     */
    @GetMapping("/upload")
    public String upload(@RequestParam("courseId") Integer courseId,
                         @RequestParam("studyProcess") Integer studyProcess,
                         @RequestHeader("userid") Integer userId) {

        courseService.upload(userId, courseId, studyProcess);

        return "ok";
    }
}
