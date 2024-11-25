package com.example.project.service;

import com.example.project.entity.Hospital;
import com.example.project.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<Hospital> getAllHospitals() {
        // Use the custom search method to get all hospitals
        return hospitalRepository.customSearchAll();
    }

    public Hospital getHospitalById(Long id) {
        // Use the custom search method to get a hospital by ID
        return hospitalRepository.customSearchById(id);
    }

    public Hospital createHospital(Hospital hospital) {
        // Use the custom insert method to create a new hospital
        hospitalRepository.customInsertHospital(
                hospital.getHospitalName(),
                hospital.getHospitalLevel(),
                hospital.getHospitalAddress(),
                hospital.getHospitalContact()
        );
        return hospital; // Return the hospital object (note: this may not have the generated ID)
    }

    public Hospital updateHospital(Long id, Hospital updatedHospital) {
        if (hospitalRepository.existsById(id)) {
            // Use the custom update method to update the hospital details
            hospitalRepository.customUpdateHospital(
                    id,
                    updatedHospital.getHospitalName(),
                    updatedHospital.getHospitalLevel(),
                    updatedHospital.getHospitalAddress(),
                    updatedHospital.getHospitalContact()
            );
            updatedHospital.setId(id); // Set the ID to the updated object
            return updatedHospital;
        } else {
            return null;
        }
    }

    public boolean deleteHospital(Long id) {
        if (hospitalRepository.existsById(id)) {
            // Use the custom delete method to remove the hospital
            hospitalRepository.customDeleteHospital(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check whether hospital's information is valid
     */
    public boolean checkHospital(String hospitalLevel) {
        return hospitalLevel.equals("High") || hospitalLevel.equals("Medium") || hospitalLevel.equals("Low");
    }
}
