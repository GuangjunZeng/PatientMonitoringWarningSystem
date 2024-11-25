package com.example.project.repository;

import com.example.project.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    /**
     * Custom method to insert a hospital (handled by save() method of JpaRepository)
     * No need for a separate method because JpaRepository's save() can handle this.
     */

    /**
     * Custom method to delete a hospital by ID (handled by deleteById() method of JpaRepository)
     * No need for a separate method because JpaRepository's deleteById() can handle this.
     */

    /**
     * Custom method to update a hospital (handled by save() method of JpaRepository)
     * No need for a separate method because JpaRepository's save() can handle this.
     */

    /**
     * Custom query to search for a hospital by ID
     * @param hospitalId the ID of the hospital
     * @return the Hospital entity
     */
    @Query("SELECT h FROM Hospital h WHERE h.hospitalId = :hospitalId")
    Hospital customSearchById(Long hospitalId);

    /**
     * Custom query to search for all hospitals
     * @return a list of all Hospital entities
     */
    @Query("SELECT h FROM Hospital h")
    List<Hospital> customSearchAll();

    /**
     * Custom method to insert a hospital using a native query (if necessary)
     * Example provided for learning purposes
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO hospital (hospital_name, hospital_level, hospital_address, hospital_contact) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void customInsertHospital(String hospitalName, String hospitalLevel, String hospitalAddress, String hospitalContact);

    /**
     * Custom method to update a hospital using a native query (if necessary)
     * Example provided for learning purposes
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE hospital SET hospital_name = ?2, hospital_level = ?3, hospital_address = ?4, hospital_contact = ?5 WHERE hospital_id = ?1", nativeQuery = true)
    void customUpdateHospital(Long hospitalId, String hospitalName, String hospitalLevel, String hospitalAddress, String hospitalContact);

    /**
     * Custom method to delete a hospital using a native query (if necessary)
     * Example provided for learning purposes
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM hospital WHERE hospital_id = ?1", nativeQuery = true)
    void customDeleteHospital(Long hospitalId);


}
