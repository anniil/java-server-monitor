package com.anil.webSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.anil.dto.MetricsDTO;
import com.anil.serverState.ServiceState;
import com.anil.serverState.ServiceStateManager;
import com.anil.util.SystemMetricsUtil;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MetricsPublisher {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public static volatile ServiceState status = ServiceState.RUNNING;

    @Scheduled(fixedRate = 1000)
    public void publishMetrics() {

        if (status != ServiceState.RUNNING) {
            return; // 🔥 REAL EFFECT
        }
        
        MetricsDTO metricsDTO = SystemMetricsUtil.getMetrics();
        messagingTemplate.convertAndSend("/topic/metrics", metricsDTO);

        // System.out.println("sent..." + metricsDTO);
    }

}
