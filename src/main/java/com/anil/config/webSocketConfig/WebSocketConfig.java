package com.anil.config.webSocketConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private WebSocketAuthInterceptor authInterceptor;

    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/ws") // WebSocket endpoint
                .setAllowedOriginPatterns("*") // Allow frontend dev server
                .addInterceptors(authInterceptor) // Attach JWT validation interceptor
                .withSockJS(); // Fallback for browsers without WS
    }
    
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic/metrics", "/topic/service-status"); // Client will subscribe to /topic/*
        registry.setApplicationDestinationPrefixes("/app"); // App messages prefix (not needed for metrics)
    }

}
