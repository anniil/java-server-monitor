package com.anil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Metric;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anil.dto.MetricsDTO;
import com.anil.util.SystemMetricsUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")

public class MetricsController {

    @Autowired
    private SystemMetricsUtil util;

    @GetMapping("/metrics")
    public MetricsDTO getData() {
        return util.getMetrics();
    }

}
