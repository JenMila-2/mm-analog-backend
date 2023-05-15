package com.example.mmanalog.repositories;

import com.example.mmanalog.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
