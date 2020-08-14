package ru.mike.adapter.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
import ru.mike.adapter.dto.MsgA;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig implements RabbitListenerConfigurer
{
    public static final String QUEUE_MSG_A = "msg-a-queue";
    public static final String QUEUE_MSG_B = "msg-b-queue";
    public static final String EXCHANGE_ORDERS = "orders-exchange";
    public static final String QUEUE_DEAD_ORDERS = "orders-dead-queue";

    @Bean
    Queue ordersQueue() {
        return QueueBuilder.durable(QUEUE_MSG_B).build();
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(QUEUE_DEAD_ORDERS).build();
    }

    @Bean
    Exchange ordersExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_ORDERS).build();
    }

    @Bean
    Binding binding(Queue ordersQueue, TopicExchange ordersExchange) {
        return BindingBuilder.bind(ordersQueue).to(ordersExchange).with(QUEUE_MSG_B);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

//    @Bean
//    public Jackson2JsonMessageConverter jsonMessageConverter() {
//        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
//        jsonConverter.setClassMapper(classMapper());
//        return jsonConverter;
//    }
//
//    @Bean
//    public DefaultClassMapper classMapper() {
//        DefaultClassMapper classMapper = new DefaultClassMapper();
//        Map<String, Class<?>> idClassMapping = new HashMap<>();
//        idClassMapping.put("MsgA", MsgA.class);
//        classMapper.setIdClassMapping(idClassMapping);
//        return classMapper;
//    }
}
