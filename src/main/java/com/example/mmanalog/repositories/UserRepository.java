package com.example.mmanalog.repositories;

import com.example.mmanalog.models.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAllUsersByEmail(String email);

    User findUserByEmail(String email);
}
