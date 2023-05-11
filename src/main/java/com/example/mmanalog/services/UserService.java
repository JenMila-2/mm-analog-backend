package com.example.mmanalog.services;

import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.repositories.UserRepository;
import com.example.mmanalog.models.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repos;

    public UserService(UserRepository repos) {
        this.repos = repos;
    }
    public Long createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.name);
        user.setEmail(userDto.email);
        user.setPassword(userDto.password);

        repos.save(user);

        return user.getId();
    }
}
