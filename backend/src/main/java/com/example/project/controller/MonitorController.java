package com.example.project.controller;

import com.example.project.entity.Monitor;
import com.example.project.entity.User;
import com.example.project.service.MonitorService;
import com.example.project.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monitors")
public class MonitorController {

    private final MonitorService monitorService;

    @Autowired
    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    // 添加监控数据
    @PostMapping
    public ResponseEntity<Monitor> addMonitor(@RequestBody Monitor monitor) {
        Monitor createdMonitor = monitorService.insert(monitor);
        return ResponseEntity.ok(createdMonitor);
    }

    // 根据用户 ID 查询监控数据
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Monitor>> getMonitorsByUser(@PathVariable Long userId) {
        List<Monitor> monitors = monitorService.searchByUser(userId);
        return ResponseEntity.ok(monitors);
    }

    // 根据监控 ID 查询单条监控数据
    @GetMapping("/{monitorId}")
    public ResponseEntity<Monitor> getMonitorById(@PathVariable Long monitorId) {
        Monitor monitor = monitorService.searchById(monitorId);
        if (monitor != null) {
            return ResponseEntity.ok(monitor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 查询所有监控数据
    @GetMapping
    public ResponseEntity<List<Monitor>> getAllMonitors() {
        List<Monitor> monitors = monitorService.searchAll();
        return ResponseEntity.ok(monitors);
    }

    // 更新监控数据
    @PutMapping("/{monitorId}")
    public ResponseEntity<Monitor> updateMonitor(@PathVariable Long monitorId, @RequestBody Monitor monitor) {
        Monitor updatedMonitor = monitorService.update(monitorId, monitor);
        if (updatedMonitor != null) {
            return ResponseEntity.ok(updatedMonitor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
