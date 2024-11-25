package com.example.project.repository;

import com.example.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    // Query method to find a user by username
    User findByUserName(String userName);

    // Custom query to find a user by role and name
    @Query("SELECT u FROM User u WHERE u.userRole = :userRole AND u.userName = :userName")
    User findByUserRoleAndUserName(@Param("userRole") String userRole, @Param("userName") String userName);

    // Spring Data JPA automatically provides methods like save, deleteById, findById, and findAll.

}
