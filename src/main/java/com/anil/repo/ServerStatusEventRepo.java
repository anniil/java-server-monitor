package com.anil.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anil.dto.ServerStatusEvent;

public interface ServerStatusEventRepo extends JpaRepository<ServerStatusEvent, Long> {

    List<ServerStatusEvent> findTop5ByOrderByIdDesc();
}
