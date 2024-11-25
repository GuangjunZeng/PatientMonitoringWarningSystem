package com.example.project.repository;

import com.example.project.entity.UserHospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserHospitalRepository extends JpaRepository<UserHospital, Long> {

    // Custom method to update discharge date
    @Query("UPDATE UserHospital u SET u.dischargeDate = :dischargeDate WHERE u.userHospitalId = :userHospitalId")
    void updateDischargeDate(@Param("userHospitalId") Long userHospitalId, @Param("dischargeDate") Date dischargeDate);

    // Custom query to retrieve all records for a specific user
    @Query("SELECT u FROM UserHospital u WHERE u.userId = :userId")
    List<UserHospital> findByUserId(@Param("userId") Long userId);

    // Custom query to retrieve all hospital records
    @Query("SELECT u FROM UserHospital u")
    List<UserHospital> findAllHospitals();

    // Custom query for hospital statistics
    @Query("SELECT u.hospitalId AS hospitalId, COUNT(u) AS numHospitalPatients, " +
            "(SELECT COUNT(u2) FROM UserHospital u2 WHERE u2.hospitalId = u.hospitalId AND u2.dischargeDate IS NULL) AS numHospitalizingPatients " +
            "FROM UserHospital u GROUP BY u.hospitalId")
    List<List<String>> getStatistics();
}
