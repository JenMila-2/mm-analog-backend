package com.example.mmanalog.repositories;

import com.example.mmanalog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    User findUserByUsername(String username);
}
