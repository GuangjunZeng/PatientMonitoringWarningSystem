package com.example.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hospital") // 映射到数据库表名
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键自增
    @Column(name = "hospital_id") // 数据库字段名
    private Long hospitalId;

    @Column(name = "hospital_name", nullable = false) //
    private String hospitalName;

    @Column(name = "hospital_level", nullable = false) //
    private String hospitalLevel;

    @Column(name = "hospital_address", nullable = false) //
    private String hospitalAddress;

    @Column(name = "hospital_contact", nullable = false) //
    private String hospitalContact;

    // 默认构造函数
    public Hospital() {}

    // 构造函数（不带 ID，用于创建新对象）
    public Hospital(String hospitalName, String hospitalLevel, String hospitalAddress, String hospitalContact) {
        this.hospitalName = hospitalName;
        this.hospitalLevel = hospitalLevel;
        this.hospitalAddress = hospitalAddress;
        this.hospitalContact = hospitalContact;
    }

    // 构造函数（带 ID，用于更新或完整对象表示）
    public Hospital(Long hospitalId, String hospitalName, String hospitalLevel, String hospitalAddress, String hospitalContact) {
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.hospitalLevel = hospitalLevel;
        this.hospitalAddress = hospitalAddress;
        this.hospitalContact = hospitalContact;
    }

    // Getter 和 Setter 方法
    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(String hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getHospitalContact() {
        return hospitalContact;
    }

    public void setHospitalContact(String hospitalContact) {
        this.hospitalContact = hospitalContact;
    }

    // toString 方法
    @Override
    public String toString() {
        return hospitalId + "|" + hospitalName + "|" + hospitalLevel + "|" + hospitalAddress + "|" + hospitalContact;
    }
}
