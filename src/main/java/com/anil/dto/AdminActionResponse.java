package com.anil.dto;

public class AdminActionResponse {
    
    private String action;
    private String status;
    private String message;
    private long timestamp;
    private String serviceState;
    
    public AdminActionResponse(String action, String status, String message, long timestamp, String serviceState) {
        this.action = action;
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.serviceState = serviceState;
    }
    
    // getters
    public String getAction() {
        return action;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
    
    public String getServiceState() {
        return serviceState;
    }

}
