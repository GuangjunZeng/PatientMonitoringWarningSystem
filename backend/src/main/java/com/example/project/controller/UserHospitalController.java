package com.example.project.controller;

import com.example.project.entity.UserHospital;
import com.example.project.service.UserHospitalService;
import com.example.project.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-hospitals")
public class UserHospitalController {

    private final UserHospitalService userHospitalService;

    @Autowired
    public UserHospitalController(UserHospitalService userHospitalService) {
        this.userHospitalService = userHospitalService;
    }

    // 添加用户住院记录
    @PostMapping
    public ResponseEntity<UserHospital> insertUserHospital(@RequestBody UserHospital userHospital) {
        UserHospital createdUserHospital = userHospitalService.insert(userHospital);
        return ResponseEntity.ok(createdUserHospital);
    }

    // 删除用户住院记录
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserHospital(@PathVariable Long id) {
        boolean isDeleted = userHospitalService.delete(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 更新用户住院记录
    @PutMapping("/{id}")
    public ResponseEntity<UserHospital> updateUserHospital(@PathVariable Long id, @RequestBody UserHospital userHospital) {
        UserHospital updatedUserHospital = userHospitalService.update(id, userHospital);
        if (updatedUserHospital != null) {
            return ResponseEntity.ok(updatedUserHospital);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 查询单个用户住院记录
    @GetMapping("/{id}")
    public ResponseEntity<UserHospital> getUserHospitalById(@PathVariable Long id) {
        UserHospital userHospital = userHospitalService.searchById(id);
        if (userHospital != null) {
            return ResponseEntity.ok(userHospital);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 查询所有用户住院记录
    @GetMapping
    public ResponseEntity<List<UserHospital>> getAllUserHospitals() {
        List<UserHospital> userHospitals = userHospitalService.searchAll();
        return ResponseEntity.ok(userHospitals);
    }

    // 获取统计数据
    @GetMapping("/statistics")
    public ResponseEntity<List<List<String>>> getStatistics() {
        List<List<String>> statistics = userHospitalService.getStatistics();
        return ResponseEntity.ok(statistics);
    }
}
