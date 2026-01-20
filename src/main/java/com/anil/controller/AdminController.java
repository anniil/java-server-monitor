package com.anil.controller;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anil.dto.AdminActionRequest;
import com.anil.dto.AdminActionResponse;
import com.anil.serverState.ServiceState;
import com.anil.serverState.ServiceStateManager;
import com.anil.util.ServerStatusUtil;
import com.anil.webSocket.MetricsPublisher;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/admin")
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
@RequiredArgsConstructor

public class AdminController {
    
    private final SimpMessagingTemplate template;
    private final ServerStatusUtil serverStatusUtil;

    @PostMapping("/start")
    public void start() {
        MetricsPublisher.status = ServiceState.RUNNING;
        serverStatusUtil.sendStatus("Service started");
    }

    @PostMapping("/stop")
    public void stop() {
        MetricsPublisher.status = ServiceState.STOPPED;
        serverStatusUtil.sendStatus("Service stopped");
    }

    @PostMapping("/restart")
    public void restart() {
        
        MetricsPublisher.status = ServiceState.RESTARTING;
        serverStatusUtil.sendStatus("Service restarting...");

        new Thread(() -> {
            try {
                Thread.sleep(4000);
                MetricsPublisher.status = ServiceState.RUNNING;
                serverStatusUtil.sendStatus("Service restarted successfully");
            } catch (Exception ignored) {
            }
        }).start();
    }

}
