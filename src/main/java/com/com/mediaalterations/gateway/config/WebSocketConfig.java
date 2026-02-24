package com.com.mediaalterations.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${websocket.relay.host}")
    private String relayHost;

    @Value("${websocket.relay.port}")
    private Integer relayPort;

    @Value("${websocket.relay.username}")
    private String username;

    @Value("${websocket.relay.password}")
    private String password;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // The client must send to -> app/kill/{processId}
        // Client -> Server
        registry.setApplicationDestinationPrefixes("/app");

        // Server -> Client
        registry.enableStompBrokerRelay("/topic")
                .setRelayHost(relayHost)
                .setRelayPort(relayPort)
                .setClientLogin(username)
                .setClientPasscode(password)
                .setSystemLogin(username)
                .setSystemPasscode(password);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-progress")
                .setAllowedOriginPatterns("*");
    }
}
