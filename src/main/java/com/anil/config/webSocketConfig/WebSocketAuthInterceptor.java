package com.anil.config.webSocketConfig;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.anil.service.JwtService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class WebSocketAuthInterceptor implements HandshakeInterceptor {

    @Autowired
    private JwtService jwt;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {

        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        String token = null;
        // String username = null;

        if (servletRequest.getCookies() != null) {
            for (var cookie : servletRequest.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    token = cookie.getValue();
                    System.out.println(token + " Anil");
                    break;
                }
            }
        }

        if (token == null || !jwt.isTokenValid(token)) {
            System.out.println("WebSocket blocked:Invalid token");
            return false;
        }

        String username = jwt.extractUsername(token).orElse(null);
        attributes.put("username", username);

        System.out.println("HandShake done " + username);
        return true;
        
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Exception exception) {
        
    }

}
