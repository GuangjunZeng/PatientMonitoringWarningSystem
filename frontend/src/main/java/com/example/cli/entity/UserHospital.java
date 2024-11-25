package com.example.cli.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserHospital {

    private Long userHospitalId;
    private Long userId;
    private Long hospitalId;
    private Date hospitalizationDate;
    private Date dischargeDate;

    // 默认构造函数
    public UserHospital() {}

    // 构造函数（不带 ID，用于创建新记录）
    public UserHospital(Long userId, Long hospitalId, Date hospitalizationDate, Date dischargeDate) {
        this.userId = userId;
        this.hospitalId = hospitalId;
        this.hospitalizationDate = hospitalizationDate;
        this.dischargeDate = dischargeDate;
    }

    // 构造函数（带 ID，用于更新或完整对象表示）
    public UserHospital(Long userHospitalId, Long userId, Long hospitalId, Date hospitalizationDate, Date dischargeDate) {
        this.userHospitalId = userHospitalId;
        this.userId = userId;
        this.hospitalId = hospitalId;
        this.hospitalizationDate = hospitalizationDate;
        this.dischargeDate = dischargeDate;
    }

    // Getter 和 Setter 方法
    public Long getUserHospitalId() {
        return userHospitalId;
    }

    public void setUserHospitalId(Long userHospitalId) {
        this.userHospitalId = userHospitalId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Date getHospitalizationDate() {
        return hospitalizationDate;
    }

    public void setHospitalizationDate(Date hospitalizationDate) {
        this.hospitalizationDate = hospitalizationDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    // 重写 toString 方法
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String res = userHospitalId + "|" + userId + "|" + hospitalId + "|" + sdf.format(hospitalizationDate) + "|";
        if (dischargeDate == null) {
            res += "Have not discharged";
        } else {
            res += sdf.format(dischargeDate);
        }
        return res;
    }
}

