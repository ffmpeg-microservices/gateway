package com.com.mediaalterations.gateway.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

import com.com.mediaalterations.gateway.messaging.RabbitMQProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GatewayController {

    private final RabbitMQProducer rabbitMQProducer;

    @MessageMapping("/kill/{processId}")
    public void submitAnswer(@DestinationVariable String processId) {
        log.info("Received kill request for processId={}", processId);
        rabbitMQProducer.sendKillCommand(processId);
    }
}
