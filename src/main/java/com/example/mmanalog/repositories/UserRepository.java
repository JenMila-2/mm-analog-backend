package com.example.mmanalog.repositories;

import com.example.mmanalog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllUsersByEmail(String email);

    User findUserByEmail(String email);
}
