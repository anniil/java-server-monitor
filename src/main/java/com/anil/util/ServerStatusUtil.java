package com.anil.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.anil.dto.ServerStatusEvent;
import com.anil.repo.ServerStatusEventRepo;
import com.anil.service.ServerStatusEventService;
import com.anil.webSocket.MetricsPublisher;

@Component
public class ServerStatusUtil {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ServerStatusEventService service;

    public void sendStatus(String msg) {

        System.out.println(MetricsPublisher.status + " " + msg);

        ServerStatusEvent event = new ServerStatusEvent();
        // MetricsPublisher.status.name(),
        // msg,
        // System.currentTimeMillis());

        event.setMessage(msg);
        event.setStatus(MetricsPublisher.status.name());
        event.setTime(System.currentTimeMillis());

        service.save(event);

        // System.out.println("Sending WS event: " + event);

        System.out.println(event.toString());

        template.convertAndSend("/topic/service-status", event);

    }
}
