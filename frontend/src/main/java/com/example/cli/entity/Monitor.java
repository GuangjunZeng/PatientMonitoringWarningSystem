package com.example.cli.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Monitor {

    private Long monitorId;
    private Long userHospitalId;
    private Date monitorTime;
    private double height;
    private double weight;
    private double bodyTemperature;
    private int heartRate;
    private int bloodPressureHigh;
    private int bloodPressureLow;
    private double bloodOxygen;
    private double bloodGlucose;
    private double bloodLipid;

    // 默认构造函数
    public Monitor() {}

    // 构造函数（不带 ID，用于创建新数据）
    public Monitor(Long userHospitalId, Date monitorTime, double height, double weight, double bodyTemperature,
                   int heartRate, int bloodPressureHigh, int bloodPressureLow,
                   double bloodOxygen, double bloodGlucose, double bloodLipid) {
        this.userHospitalId = userHospitalId;
        this.monitorTime = monitorTime;
        this.height = height;
        this.weight = weight;
        this.bodyTemperature = bodyTemperature;
        this.heartRate = heartRate;
        this.bloodPressureHigh = bloodPressureHigh;
        this.bloodPressureLow = bloodPressureLow;
        this.bloodOxygen = bloodOxygen;
        this.bloodGlucose = bloodGlucose;
        this.bloodLipid = bloodLipid;
    }

    // 构造函数（带 ID，用于更新或完整对象表示）
    public Monitor(Long monitorId, Long userHospitalId, Date monitorTime, double height, double weight,
                   double bodyTemperature, int heartRate, int bloodPressureHigh, int bloodPressureLow,
                   double bloodOxygen, double bloodGlucose, double bloodLipid) {
        this.monitorId = monitorId;
        this.userHospitalId = userHospitalId;
        this.monitorTime = monitorTime;
        this.height = height;
        this.weight = weight;
        this.bodyTemperature = bodyTemperature;
        this.heartRate = heartRate;
        this.bloodPressureHigh = bloodPressureHigh;
        this.bloodPressureLow = bloodPressureLow;
        this.bloodOxygen = bloodOxygen;
        this.bloodGlucose = bloodGlucose;
        this.bloodLipid = bloodLipid;
    }

    // Getter 和 Setter
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

    // toString 方法（适配前端格式化输出需求）
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Monitor ID: " + monitorId + ", User Hospital ID: " + userHospitalId +
                ", Time: " + (monitorTime != null ? sdf.format(monitorTime) : "N/A") +
                ", Height: " + height + "m, Weight: " + weight + "kg, " +
                "Body Temperature: " + bodyTemperature + "°C, Heart Rate: " + heartRate + " bpm, " +
                "Blood Pressure: " + bloodPressureHigh + "/" + bloodPressureLow + " mmHg, " +
                "Blood Oxygen: " + bloodOxygen + "%, Blood Glucose: " + bloodGlucose + " mmol/L, " +
                "Blood Lipid: " + bloodLipid + " mmol/L";
    }
}
