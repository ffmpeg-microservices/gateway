package com.com.mediaalterations.gateway.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.kill}")
    private String exchange;

    @Value("${rabbitmq.queue.kill.routingKey}")
    private String killRoutingKey;

    public void sendKillCommand(String processId) {
        log.info("Sending kill command for processId={}", processId);
        rabbitTemplate.convertAndSend(exchange, killRoutingKey, processId);
    }

}
