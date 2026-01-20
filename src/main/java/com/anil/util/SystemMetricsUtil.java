package com.anil.util;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;

import org.springframework.stereotype.Component;

import com.anil.dto.MetricsDTO;
import com.sun.management.OperatingSystemMXBean;

@Component
public class SystemMetricsUtil {

    private static final OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory
            .getOperatingSystemMXBean();

    public static MetricsDTO getMetrics() {
        MetricsDTO dto = new MetricsDTO();

        try {
            dto.setHostname(InetAddress.getLocalHost().getHostName());
        } catch (Exception e) {
            dto.setHostname("unknown");
        }

        dto.setUptime(ManagementFactory.getRuntimeMXBean().getUptime());

        // CPU Load (%)
        double cpuLoad = osBean.getCpuLoad();
        dto.setCpuPercent(cpuLoad < 0 ? 0 : cpuLoad * 100);

        // Memory
        long totalMemory = osBean.getTotalMemorySize();
        long freeMemory = osBean.getFreeMemorySize();
        dto.setMemoryUsed(totalMemory - freeMemory);
        dto.setMemoryTotal(totalMemory);
        
        // Disk
        File root = new File("/");
        dto.setDiskTotal(root.getTotalSpace());
        dto.setDiskUsed(root.getTotalSpace() - root.getFreeSpace());

        return dto;
    }
}
