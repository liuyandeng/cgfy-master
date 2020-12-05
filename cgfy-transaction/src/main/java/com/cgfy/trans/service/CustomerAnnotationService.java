package com.cgfy.trans.service;

import com.cgfy.trans.domain.model.Customer;
import com.cgfy.trans.domain.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * spring 事务注解形式
 */
@Service
@Slf4j
public class CustomerAnnotationService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    public Customer create(Customer customer) {
        customer.setUsername(customer.getUsername());
        customer = customerRepository.save(customer);
        jmsTemplate.convertAndSend("annotationQueue", customer.getUsername());
        if ("admin".equals(customer.getUsername())) { //如果在db和mq之后报错,数据会回滚,消息不会发送
            throw new RuntimeException("管理员用户已经存在");
        }
        return customer;
    }
    @Transactional
    @JmsListener(destination = "annotationQueue", containerFactory = "msgFactory")
    public void createByListener(String name) throws Exception {
        Customer customer = new Customer();
        customer.setUsername(name);
        customer.setRole("ANNOTATION_ROLE");
        customer.setPassword("123456");
        customerRepository.save(customer);
        log.info("用户"+name+"通过消息注解方式被创建");
//        if ("admin".equals(name)) {//消息消费者失败后,生产者没有回滚,因为别的消费者还能消费它，直至消费成功为止。在create落库了，也成功发消息了这个事务就算做完了。剩下的事儿就靠消费者自己折腾了。
//            throw new RuntimeException("管理员用户已经存在");
//        }
    }

}
