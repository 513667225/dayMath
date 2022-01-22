package com.yph.util;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MQ发消息帮助类
 *
 * @author Agu
 */

//@Component
public class MqUtil {

    //xx  xxQueue xx.key  xxExchange
    //
//    @Autowired
    RabbitTemplate rabbitTemplate;

    public void testSend(String exchange, String routingKey, Object obj) {
        testSend(exchange, routingKey, obj, null);
    }

    public void testSend(String exchange, String routingKey, Object obj, CorrelationData correlationData) {
        if (obj instanceof String) {
            rabbitTemplate.convertAndSend(exchange, routingKey, obj, correlationData);
        } else {
            rabbitTemplate.convertAndSend(exchange, routingKey, JSONUtils.toString(obj), correlationData);
        }
    }

}
