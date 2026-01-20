package com.anil.serverState;

import org.springframework.stereotype.Component;

@Component
public class ServiceStateManager {

    private volatile ServiceState state = ServiceState.RUNNING;

    public ServiceState getState() {
        return state;
    }
    
    public void setState(ServiceState state) {
        this.state = state;
    }
}
