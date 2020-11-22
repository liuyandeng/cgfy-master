package com.cgfy.trans.config;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;

/**
 * mq连接:ConnectionFactory
 * 消息模板:JmsTemplate
 * 监听器:JmsListenerContainerFactory
 * 事务:PlatformTransactionManager/JmsTransactionManager
 */

@Configuration
@Slf4j
public class BeanConfig {
    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.user}")
    private String username;

    @Value("${spring.activemq.password}")
    private String password;



    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(username, password, brokerUrl);
    }


    @Bean
    public JmsTemplate initJmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory);
        //正常情况下,mq发出消息后会立即执行,但是在分布式事务执行过程中,会有这样的一种情况,
        // 即方法执行后,事务成功提交才希望消息执行,这样就需要配置一下JmsTemplate
        template.setSessionTransacted(true);//设置是否使用事务，默认为false,很关键
        return template;
    }


    //这个用于设置 @JmsListener使用的containerFactory
    @Bean
    public JmsListenerContainerFactory<?> msgFactory(ConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer,
                                                     PlatformTransactionManager transactionManager) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setTransactionManager(transactionManager);
        factory.setReceiveTimeout(10000L);
        factory.setPubSubDomain(false);
        configurer.configure(factory, connectionFactory);
        return factory;
    }



}