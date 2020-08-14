package ru.mike.adapter.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mike.adapter.config.RabbitConfig;
import ru.mike.adapter.dto.MsgA;
import ru.mike.adapter.dto.MsgB;
import ru.mike.adapter.enricher.OpenWeatherMessageEnricher;
import ru.mike.adapter.service.OrderMessageSender;

@Component
public class OrderMessageListener {
    static final Logger logger = LoggerFactory.getLogger(OrderMessageListener.class);

    @Autowired
    OpenWeatherMessageEnricher openWeatherMessageEnricher;

    @Autowired
    OrderMessageSender orderMessageSender;

    @RabbitListener(queues = RabbitConfig.QUEUE_MSG_A)
    public void processOrder(MsgA msgA) {
        logger.info("Message Received: "+ msgA);

        if (!msgA.getMsg().isEmpty() && msgA.getLng().equals("ru")) {
            MsgB msgB = openWeatherMessageEnricher.enrich(msgA);
            orderMessageSender.sendOrder(msgB);
            logger.info("Message Send:" + msgA);
        } else {
            logger.info("Incorrect Message: " + msgA);
        }
    }
}
