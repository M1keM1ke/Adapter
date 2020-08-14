package ru.mike.adapter.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mike.adapter.config.RabbitConfig;
import ru.mike.adapter.dto.MsgB;

@Service
public class OrderMessageSender {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public OrderMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrder(MsgB msgB) {
        try {
            String orderJson = objectMapper.writeValueAsString(msgB);
            Message message = MessageBuilder
                    .withBody(orderJson.getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();
            this.rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_MSG_B, message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
