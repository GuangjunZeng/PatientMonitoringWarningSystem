package com.example.project.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "monitor")
public class Monitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monitorId;

    @Column(name = "user_hospital_id", nullable = false)
    private Long userHospitalId;

    @Column(name = "monitor_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date monitorTime;

    @Column(name = "height", nullable = false)
    private double height;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "body_temperature", nullable = false)
    private double bodyTemperature;

    @Column(name = "heart_rate", nullable = false)
    private int heartRate;

    @Column(name = "blood_pressure_high", nullable = false)
    private int bloodPressureHigh;

    @Column(name = "blood_pressure_low", nullable = false)
    private int bloodPressureLow;

    @Column(name = "blood_oxygen", nullable = false)
    private double bloodOxygen;

    @Column(name = "blood_glucose", nullable = false)
    private double bloodGlucose;

    @Column(name = "blood_lipid", nullable = false)
    private double bloodLipid;

    // Getters and Setters

    public Long getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Long monitorId) {
        this.monitorId = monitorId;
    }

    public Long getUserHospitalId() {
        return userHospitalId;
    }

    public void setUserHospitalId(Long userHospitalId) {
        this.userHospitalId = userHospitalId;
    }

    public Date getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(Date monitorTime) {
        this.monitorTime = monitorTime;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getBloodPressureHigh() {
        return bloodPressureHigh;
    }

    public void setBloodPressureHigh(int bloodPressureHigh) {
        this.bloodPressureHigh = bloodPressureHigh;
    }

    public int getBloodPressureLow() {
        return bloodPressureLow;
    }

    public void setBloodPressureLow(int bloodPressureLow) {
        this.bloodPressureLow = bloodPressureLow;
    }

    public double getBloodOxygen() {
        return bloodOxygen;
    }

    public void setBloodOxygen(double bloodOxygen) {
        this.bloodOxygen = bloodOxygen;
    }

    public double getBloodGlucose() {
        return bloodGlucose;
    }

    public void setBloodGlucose(double bloodGlucose) {
        this.bloodGlucose = bloodGlucose;
    }

    public double getBloodLipid() {
        return bloodLipid;
    }

    public void setBloodLipid(double bloodLipid) {
        this.bloodLipid = bloodLipid;
    }
}
