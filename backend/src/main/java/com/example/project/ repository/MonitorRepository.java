package com.example.project.repository;

import com.example.project.entity.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {
    // 根据用户 ID 查询监控数据
    List<Monitor> findByUserHospital_UserId(Long userId);

    // Custom insert operation is handled by JpaRepository's save method.

    // Custom method to search for monitor data for a specific user
    @Query("SELECT m FROM Monitor m WHERE m.userHospitalId IN " +
            "(SELECT uh.userHospitalId FROM UserHospital uh WHERE uh.userId = ?1) " +
            "ORDER BY m.userHospitalId, m.monitorTime")
    List<Monitor> findByUserId(Long userId);

    // Custom method to search for a specific monitor record by ID
    @Query("SELECT m FROM Monitor m WHERE m.monitorId = ?1")
    Monitor findMonitorById(Long monitorId);

    // Custom method to search for all monitor records
    @Query("SELECT m FROM Monitor m ORDER BY m.userHospitalId, m.monitorTime")
    List<Monitor> findAllMonitorsOrdered();

    // Custom method to update a monitor record is not needed as JPA's save method handles updates.
}
