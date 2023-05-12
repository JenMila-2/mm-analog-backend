package com.example.mmanalog.services;

import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.models.User;
import com.example.mmanalog.repositories.UserRepository;
import com.example.mmanalog.exceptions.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   public Long createUser(UserDto userDto) {
        User user = new User();

        user.setName(userDto.name);
        user.setEmail(userDto.email);
        user.setPassword(userDto.password);
        user.setEnabled(userDto.enabled);

        userRepository.save(user);

        return user.getId();
   }

    public List<UserDto> getUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {

           UserDto userDto = new UserDto();

           userDto.id = user.getId();
           userDto.name = user.getName();
           userDto.email = user.getEmail();
           userDto.password = user.getPassword();

           userDtos.add(userDto);

        }
        return userDtos;
    }

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));

        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.name = user.getName();
        userDto.email = user.getEmail();
        userDto.password = user.getPassword();

        return userDto;
    }

    public void deleteUser(@RequestBody Long id) {

        userRepository.deleteById(id);

    }

    /*public static UserDto fromUser(User user) {
        UserDto dto = new UserDto();

        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setEnabled(user.isEnabled());
        return dto;
    }*/

}
