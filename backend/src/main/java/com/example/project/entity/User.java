package com.example.project.entity;

import jakarta.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "user") // 映射到数据库表 "user"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键自增
    @Column(name = "user_id") // 数据库字段名
    private Long userId;

    @Column(name = "user_role", nullable = false) // 映射字段，非空
    private String userRole;

    @Column(name = "user_name", nullable = false, unique = true) // 唯一性约束
    private String userName;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_gender", nullable = false)
    private String userGender;

    @Column(name = "user_dob", nullable = false)
    @Temporal(TemporalType.DATE) // 映射为日期类型
    private Date userDob;

    @Column(name = "user_address", nullable = false)
    private String userAddress;

    @Column(name = "user_contact", nullable = false)
    private String userContact;

    // 默认构造函数
    public User() {
    }

    // 构造函数（不带 ID，用于创建新记录）
    public User(String userRole, String userName, String userPassword, String userGender,
                Date userDob, String userAddress, String userContact) {
        this.userRole = userRole;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userGender = userGender;
        this.userDob = userDob;
        this.userAddress = userAddress;
        this.userContact = userContact;
    }

    // 构造函数（带 ID，用于更新或完整对象表示）
    public User(Long userId, String userRole, String userName, String userPassword, String userGender,
                Date userDob, String userAddress, String userContact) {
        this.userId = userId;
        this.userRole = userRole;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userGender = userGender;
        this.userDob = userDob;
        this.userAddress = userAddress;
        this.userContact = userContact;
    }

    // Getters 和 Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Date getUserDob() {
        return userDob;
    }

    public void setUserDob(Date userDob) {
        this.userDob = userDob;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    // 重写 toString 方法
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return userId + "|" + userRole + "|" + userName + "|" + userPassword + "|" +
                userGender + "|" + sdf.format(userDob) + "|" + userAddress + "|" + userContact;
    }
}
