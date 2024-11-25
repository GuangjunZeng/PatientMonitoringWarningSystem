package com.example.cli.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

    private Long userId;
    private String userRole;
    private String userName;
    private String userPassword;
    private String userGender;
    private Date userDob;
    private String userAddress;
    private String userContact;

    // 默认构造函数
    public User() {
    }

    // 构造函数（不带 ID，用于创建新用户）
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

    // toString 方法（适配前端格式化输出需求）
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "User ID: " + userId +
                ", Role: " + userRole +
                ", Name: " + userName +
                ", Password: " + userPassword +
                ", Gender: " + userGender +
                ", DOB: " + (userDob != null ? sdf.format(userDob) : "N/A") +
                ", Address: " + userAddress +
                ", Contact: " + userContact;
    }
}
