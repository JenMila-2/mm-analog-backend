package com.example.mmanalog.services;

import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.models.User;
import com.example.mmanalog.repositories.UserRepository;
import com.example.mmanalog.exceptions.UserNotFoundException;

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

    public UserDto getUser(Long id) {
        User user = repos.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.name = user.getName();
        userDto.email = user.getEmail();
        userDto.password = user.getPassword();

        return userDto;
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
