package com.anil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anil.service.SystemService;

@RestController
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private SystemService service;

    @GetMapping("/hostname")
    public String hostname() {
        return service.getHostname();
    }

    @GetMapping("/uptime")
    public String uptime() {
        return service.getUptime();
    }

    @GetMapping("/cpu")
    public String cpu() {
        return service.getCpu();
    }

    @GetMapping("/memory")
    public String memory() {
        return service.getMemory();
    }

    @GetMapping("/disk")
    public String disk() {
        return service.getDisk();
    }

}
