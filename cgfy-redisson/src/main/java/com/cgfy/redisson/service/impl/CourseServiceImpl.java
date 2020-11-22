package com.cgfy.redisson.service.impl;

import com.alibaba.fastjson.JSON;

import com.cgfy.redisson.domain.model.CourseModel;
import com.cgfy.redisson.domain.model.CourseRecordModel;
import com.cgfy.redisson.domain.repository.CourseRecordRepository;
import com.cgfy.redisson.domain.repository.CourseRepository;
import com.cgfy.redisson.service.CourseService;
import com.cgfy.redisson.utils.RedisKeyPrefixConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 课程服务
 */
@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseRecordRepository courseRecordRepository;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedissonClient client;

    /**
     * 通过id获取课程
     * @param courseId
     * @return
     */
    @Override
    public CourseModel getById(Integer courseId) {
        CourseModel courseModel = null;
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        String value = hashOperations.get(RedisKeyPrefixConstant.COURSE, String.valueOf(courseId));//课程是否存在redis中

        if (StringUtils.isBlank(value)) {
            String lockKey = RedisKeyPrefixConstant.LOCK_COURSE + courseId;
            RLock lock = client.getLock(lockKey);
            try {

                boolean res = lock.tryLock(10, TimeUnit.SECONDS);//尝试加锁,最多等待10s
                if (res) {
                    value = hashOperations.get(RedisKeyPrefixConstant.COURSE, String.valueOf(courseId));
                    if (StringUtils.isBlank(value)) {
                        log.info("从数据库中读取");
                        courseModel = courseRepository.findById(courseId).orElse(null);
                        hashOperations.put(RedisKeyPrefixConstant.COURSE, String.valueOf(courseId), JSON.toJSONString(courseModel));//课程存入redis
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            log.info("从缓存中读取");
            courseModel = JSON.parseObject(value, CourseModel.class);
        }

        return courseModel;
    }

    @Override
    public void upload(Integer userId, Integer courseId, Integer studyProcess) {

        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();

        String cacheKey = RedisKeyPrefixConstant.COURSE_PROGRESS + ":" + userId;
        String cacheValue = hashOperations.get(cacheKey, String.valueOf(courseId));//根据用户id从redis获取课程
        if (StringUtils.isNotBlank(cacheValue) && studyProcess <= Integer.valueOf(cacheValue)) {
            return;
        }

        String lockKey = "upload:" + userId + ":" + courseId;

        RLock lock = client.getLock(lockKey);

        try {
            lock.lock(10, TimeUnit.SECONDS);

            cacheValue = hashOperations.get(cacheKey, String.valueOf(courseId));
            if (StringUtils.isBlank(cacheValue) || studyProcess > Integer.valueOf(cacheValue)) {
                CourseRecordModel model = new CourseRecordModel();
                model.setUserId(userId);
                model.setCourseId(courseId);
                model.setStudyProcess(studyProcess);
                courseRecordRepository.save(model);
                hashOperations.put(cacheKey, String.valueOf(courseId), String.valueOf(studyProcess));
            }

        } catch (Exception ex) {
            log.error("获取所超时！", ex);
        } finally {
            lock.unlock();
        }

    }
}
