package com.cgfy.rabbitmq.bean;

import lombok.Data;

@Data
public class QueueBean {
    private String auto_delete;
    private String durable;
    private String vhost;
    private String name;
    private String state;

}
