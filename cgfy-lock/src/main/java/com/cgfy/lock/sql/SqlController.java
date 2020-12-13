package com.cgfy.lock.sql;
import com.cgfy.lock.base.bean.AjaxResponse;
import com.cgfy.lock.base.dao.JBaseDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "SQL锁", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class SqlController {
    @Resource
    public JBaseDao jBaseDao;

    /**
     * 把 for update 放在 Spring 事务中执行
     * 事务不提交，for update 悲观锁不会被释放；
     * 不加 Spring 事务并发执行 for update 语句，如果有两个以上的不同 ID 的 connection 执行 for update，会发生阻塞现象，Mysql 则不会阻塞；
     * 不加 Spring 事务并发执行 for update 语句，并且 druid 连接池的 autocommit=false，不会发生阻塞；
     * 加 Spring 事务并发执行 for update 语句，不会发生阻塞。
     * @link -https://segmentfault.com/a/1190000019367541?utm_source=tag-newest
     * @return
     */
    @ApiOperation(value = "检索")
    @GetMapping(value = "/select")
    @Transactional
    public AjaxResponse select() {
        String sql="select * from user_info  where id='666666' for update";
        List list= jBaseDao.queryForList(sql);
        return AjaxResponse.success(list);//执行到这里,你不能更改666666这一行数据
    }




}
