package com.cgfy.trans.web;

import com.cgfy.trans.domain.model.Customer;
import com.cgfy.trans.domain.repository.CustomerRepository;
import com.cgfy.trans.service.CustomerAnnotationService;
import com.cgfy.trans.service.CustomerCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    private CustomerAnnotationService service;
    @Autowired
    private CustomerCodeService codeService;

    @Autowired
    private CustomerRepository customerRepository;


    /**
     * 通过注解方式创建用户
     *
     * @param customer
     * @return
     */

    @PostMapping("/annotation/create")
    public Customer createInAnnotation(@RequestBody Customer customer) {
        return service.create(customer);
    }

    /**
     * 通过代码方式创建用户
     *
     * @param customer
     * @return
     */

    @PostMapping("/code/create")
    public Customer createInCode(@RequestBody Customer customer) {
        return codeService.create(customer);
    }

    /**
     * 消息触发方式创建用户
     *
     * @param userName
     * @return
     */

    @PostMapping("/sendMsg")
    public void createMsgWithListener(@RequestParam String userName) {
        jmsTemplate.convertAndSend("testQueue", userName);
    }


    @GetMapping("")
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

}
