package com.com.mediaalterations.gateway.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.com.mediaalterations.gateway.dto.FfmpegCmdResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProgressListener {

    @RabbitListener(queues = "gateway.progress.queue")
    public void handleAllOrderEvents(FfmpegCmdResponse event) {
        log.info("Received: {}", event);

    }

}
