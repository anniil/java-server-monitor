package com.anil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anil.dto.ServerStatusEvent;
import com.anil.service.ServerStatusEventService;

@RestController
@RequestMapping("/api/service-status")
public class ServiceStatusEventController {

    @Autowired
    private ServerStatusEventService service;

    @GetMapping("/recent")
    public List<ServerStatusEvent> recent(
            @RequestParam(defaultValue = "5") int limit) {

        return service.getRecentEvents(limit);
    }

}
