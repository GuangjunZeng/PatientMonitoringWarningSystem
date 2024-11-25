package com.example.project.service;

import com.example.project.entity.UserHospital;
import com.example.project.repository.UserHospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserHospitalService {

    private final UserHospitalRepository userHospitalRepository;

    @Autowired
    public UserHospitalService(UserHospitalRepository userHospitalRepository) {
        this.userHospitalRepository = userHospitalRepository;
    }

    // Add a new user hospitalization record
    public UserHospital insert(UserHospital userHospital) {
        return userHospitalRepository.save(userHospital);
    }

    // Delete a user hospitalization record
    public boolean delete(Long id) {
        if (userHospitalRepository.existsById(id)) {
            userHospitalRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Update the discharge date of a user hospitalization record
    public boolean updateDischargeDate(Long id, Date dischargeDate) {
        if (userHospitalRepository.existsById(id)) {
            userHospitalRepository.updateDischargeDate(id, dischargeDate);
            return true;
        }
        return false;
    }

    // Retrieve a user hospitalization record by ID
    public UserHospital searchById(Long id) {
        return userHospitalRepository.findById(id).orElse(null);
    }

    // Retrieve all user hospitalization records for a specific user
    public List<UserHospital> searchByUserId(Long userId) {
        return userHospitalRepository.findByUserId(userId);
    }

    // Retrieve all user hospitalization records
    public List<UserHospital> searchAll() {
        return userHospitalRepository.findAllHospitals();
    }

    // Get hospital statistics
    public List<List<String>> getStatistics() {
        return userHospitalRepository.getStatistics();
    }

}
