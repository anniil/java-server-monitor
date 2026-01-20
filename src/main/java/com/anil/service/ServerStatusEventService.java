package com.anil.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anil.dto.ServerStatusEvent;
import com.anil.repo.ServerStatusEventRepo;

@Service
public class ServerStatusEventService {

    @Autowired
    private ServerStatusEventRepo repo;

    @Transactional
    public void save(ServerStatusEvent serverStatusEvent) {

        System.out.println(serverStatusEvent);
        
        repo.save(serverStatusEvent);
    }
    
    @Transactional
    public List<ServerStatusEvent> getRecentEvents(int limit) {
        List<ServerStatusEvent> events = repo.findTop5ByOrderByIdDesc();

        Collections.reverse(events); // oldest → newest
        return events;
    }

}
