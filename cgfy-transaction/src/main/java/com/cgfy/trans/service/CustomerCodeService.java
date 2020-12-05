package com.cgfy.trans.service;

import com.cgfy.trans.domain.model.Customer;
import com.cgfy.trans.domain.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


@Service
@Slf4j
public class CustomerCodeService {


    @Autowired
    private CustomerRepository CustomerDao;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private JmsTemplate jmsTemplate;


    public Customer create(Customer customer) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        def.setTimeout(15);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            CustomerDao.save(customer);
            jmsTemplate.convertAndSend("codeQueue", customer.getUsername());
            transactionManager.commit(status);
            return customer;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
    @JmsListener(destination = "codeQueue", containerFactory = "msgFactory")
    public void createByListener(String name) {
        Customer customer = new Customer();
        customer.setUsername(name);
        customer.setRole("CODE_ROLE");
        customer.setPassword("123456");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        def.setTimeout(15);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            CustomerDao.save(customer);
            jmsTemplate.convertAndSend("annotationQueue", customer.getUsername());
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
}
