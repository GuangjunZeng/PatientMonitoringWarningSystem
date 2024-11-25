package com.example.project.service;

import com.example.project.entity.User;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User authenticate(String userName, String userPassword) {
        // Retrieve the user by username
        User user = userRepository.findByUserName(userName);

        // Check if user exists and the password matches
        if (user != null && user.getUserPassword().equals(userPassword)) {
            return user; // Authentication successful
        }

        return null; // Authentication failed
    }


    // 添加用户
    public User insert(User user) {
        return userRepository.save(user);
    }

    // 删除用户
    public boolean delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 更新用户
    public User update(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setUserId(id);
            return userRepository.save(user);
        }
        return null;
    }

    // 根据 ID 查询用户
    public User searchById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // 根据角色和名称查询用户
    public User searchByRoleAndName(String role, String name) {
        return userRepository.findByUserRoleAndUserName(role, name);
    }

    // 查询所有用户
    public List<User> searchAll() {
        return userRepository.findAll();
    }

    /**
     * Check whether user's information is valid
     */
    public boolean checkUser(String userRole, String userName, String userGender, Date userDob, boolean isUpdate) {
        User searchUser = userRepository.findByUserRoleAndUserName(userRole, userName);
        if (!isUpdate && searchUser != null) {
            return false;
        }
        if (!(userRole.equals("Admin") || userRole.equals("Patient"))) {
            return false;
        }
        if (!(userGender.equals("Male") || userGender.equals("Female") || userGender.equals("Unknown"))) {
            return false;
        }
        String[] userDobParts = sdf.format(userDob).split("-");
        if (userDobParts.length != 3) {
            return false;
        }
        if (userDobParts[0].length() != 4 || userDobParts[1].length() != 2 || userDobParts[2].length() != 2) {
            return false;
        }
        return true;
    }
}
