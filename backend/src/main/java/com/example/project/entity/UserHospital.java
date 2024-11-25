package com.example.project.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_hospital")

public class UserHospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userHospitalId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "hospital_id", nullable = false)
    private Long hospitalId;

    @Column(name = "hospitalization_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date hospitalizationDate;

    @Column(name = "discharge_date")
    @Temporal(TemporalType.DATE)
    private Date dischargeDate;
    public UserHospital() {

    }

    public UserHospital(Long userId, Long hospitalId, Date hospitalizationDate, Date dischargeDate) {
        this.userId = userId;
        this.hospitalId = hospitalId;
        this.hospitalizationDate = hospitalizationDate;
        this.dischargeDate = dischargeDate;
    }

    public UserHospital(Long userHospitalId, Long userId, Long hospitalId, Date hospitalizationDate, Date dischargeDate) {
        this.userHospitalId = userHospitalId;
        this.userId = userId;
        this.hospitalId = hospitalId;
        this.hospitalizationDate = hospitalizationDate;
        this.dischargeDate = dischargeDate;
    }

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
