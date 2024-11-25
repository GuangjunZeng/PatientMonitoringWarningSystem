package com.example.cli.entity;

// 前端专用的医院实体类
public class Hospital {

    private Long hospitalId;
    private String hospitalName;
    private String hospitalLevel;
    private String hospitalAddress;
    private String hospitalContact;

    // 默认构造函数
    public Hospital() {}

    // 构造函数（不带 ID）
    public Hospital(String hospitalName, String hospitalLevel, String hospitalAddress, String hospitalContact) {
        this.hospitalName = hospitalName;
        this.hospitalLevel = hospitalLevel;
        this.hospitalAddress = hospitalAddress;
        this.hospitalContact = hospitalContact;
    }

    // 构造函数（带 ID）
    public Hospital(Long hospitalId, String hospitalName, String hospitalLevel, String hospitalAddress, String hospitalContact) {
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.hospitalLevel = hospitalLevel;
        this.hospitalAddress = hospitalAddress;
        this.hospitalContact = hospitalContact;
    }

    // Getter 和 Setter
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
        return "Hospital{" +
                "hospitalId=" + hospitalId +
                ", hospitalName='" + hospitalName + '\'' +
                ", hospitalLevel='" + hospitalLevel + '\'' +
                ", hospitalAddress='" + hospitalAddress + '\'' +
                ", hospitalContact='" + hospitalContact + '\'' +
                '}';
    }
}
