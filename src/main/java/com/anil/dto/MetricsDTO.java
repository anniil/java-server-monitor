package com.anil.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MetricsDTO {
    private String hostname;

    private long uptime; // in milliseconds

    private double cpuPercent; // CPU usage %

    private long memoryUsed; // bytes
    private long memoryTotal; // bytes
    
    private long diskUsed; // bytes
    private long diskTotal;

}
