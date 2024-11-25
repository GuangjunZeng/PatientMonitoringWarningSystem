package com.example.project.service;

import com.example.project.entity.Monitor;
import com.example.project.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorService {

    private final MonitorRepository monitorRepository;

    @Autowired
    public MonitorService(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    public Monitor insert(Monitor monitor) {
        return monitorRepository.save(monitor);
    }

    public List<Monitor> searchByUser(Long userId) {
        return monitorRepository.findByUserId(userId);
    }

    public Monitor searchById(Long monitorId) {
        return monitorRepository.findById(monitorId).orElse(null);
    }

    public List<Monitor> searchAll() {
        return monitorRepository.findAll();
    }

    public Monitor update(Long monitorId, Monitor monitor) {
        if (monitorRepository.existsById(monitorId)) {
            monitor.setMonitorId(monitorId);
            return monitorRepository.save(monitor);
        }
        return null;
    }
}
