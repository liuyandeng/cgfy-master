package com.cgfy.mq.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义交换机,队列,banding
 */
@Configuration
public class MQConfig {

	public static final String MIAOSHA_QUEUE = "miaosha.queue";

	public static final String FANOUT_EXCHANGE = "fanoutxchage";//发送消息到所有与它绑定的Queue中

	public static final String TOPIC_EXCHANGE = "topicExchage";//主题交换器
	public static final String TOPIC_QUEUE1 = "topic.queue1";
	public static final String TOPIC_QUEUE2 = "topic.queue2";


	public static final String HEADERS_EXCHANGE = "headersExchage";//消息头交换器
	public static final String HEADER_QUEUE = "header.queue";

	/*********************************************Direct模式******************************************************************************/
	/**
	 * 直接交换机
	 * Queue 可以有4个参数
	 *    1.队列名
	 *    2.durable 持久化消息队列 ,rabbitmq重启的时候不需要创建新的队列 默认true
	 *    3.auto-delete   表示消息队列没有在使用时将被自动删除 默认是false
	 *    4.exclusive     表示该消息队列是否只在当前connection生效,默认是false
	 */
	@Bean
	public Queue miaoShaQueue() {
		return new Queue(MIAOSHA_QUEUE, true);
	}



	/*****************************************************Topic模式 ************************************************************************ */
	//主题交换机Exchange
	@Bean
	public TopicExchange topicExchage(){
		return new TopicExchange(TOPIC_EXCHANGE);
	}
	//主题队列1
	@Bean
	public Queue topicQueue1() {
		return new Queue(TOPIC_QUEUE1, true);
	}
	//主题队列2
	@Bean
	public Queue topicQueue2() {
		return new Queue(TOPIC_QUEUE2, true);
	}

	//主题交换机绑定主题队列1
	@Bean
	public Binding topicBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(topicExchage()).with("topic.key1");
	}
	//主题交换机绑定主题队列2
	@Bean
	public Binding topicBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(topicExchage()).with("topic.#");
	}



	/******************************************************Fanout模式*****************************************************************/
	//广播交换机Exchange,fanout类型的Exchange就会无视binding key，而是将消息路由到所有绑定到该Exchange的Queue。
	@Bean
	public FanoutExchange fanoutExchage(){
		return new FanoutExchange(FANOUT_EXCHANGE);
	}


	//广播交换机绑定队列1
	@Bean
	public Binding FanoutBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(fanoutExchage());
	}
	//广播交换机绑定队列2
	@Bean
	public Binding FanoutBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(fanoutExchage());
	}





	/***************************************************************Header模式************************************************************/
	//headers类型的Exchange不依赖于routing key与binding key
	//消息体交换机Exchange
	@Bean
	public HeadersExchange headersExchage(){
		return new HeadersExchange(HEADERS_EXCHANGE);
	}
	//消息体队列1
	@Bean
	public Queue headerQueue1() {
		return new Queue(HEADER_QUEUE, true);
	}
	//消息体交换机绑定消息体队列1
	@Bean
	public Binding headerBinding() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("header1", "value1");
		map.put("header2", "value2");
		return BindingBuilder.bind(headerQueue1()).to(headersExchage()).whereAll(map).match();
	}


}
