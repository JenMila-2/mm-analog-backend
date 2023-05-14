package com.example.mmanalog.services;

import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.exceptions.InvalidPasswordException;
import com.example.mmanalog.models.User;
import com.example.mmanalog.repositories.UserRepository;
import com.example.mmanalog.exceptions.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    //private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getUsers() {
        Iterable<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
           userDtos.add(transferUserToDto(user));
        }
        return userDtos;
    }

    public UserDto getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return transferUserToDto(user);
        } else {
            throw new UserNotFoundException("No user found with this id: " + id);
        }
    }

    public UserDto addUser(UserDto dtoUser) {

        User user = transferToUser(dtoUser);
        userRepository.save(user);

        return transferUserToDto(user);
    }

    public void deleteUser(@RequestBody Long id) {
        userRepository.deleteById(id);
    }

    public UserDto updateUser(Long id, UserDto newUser) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user1 = userOptional.get();

            user1.setName(newUser.getName());
            user1.setEmail(newUser.getEmail());
            user1.setPassword(newUser.getPassword());

            User returnUser = userRepository.save(user1);

            return transferUserToDto(returnUser);

        } else {
            throw new UserNotFoundException("No user found with this id: " + id);
        }
    }

    public User transferToUser(UserDto userDto) {

       var user = new User();

       user.setId(userDto.getId());
       user.setName(userDto.getName());
       user.setEmail(userDto.getEmail());
       user.setPassword(userDto.getPassword());
       user.setEnabled(userDto.isEnabled());

       return user;
    }

    public UserDto transferUserToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.id = user.getId();
        userDto.name = user.getName();
        userDto.email = user.getEmail();
        userDto.password = user.getPassword();
        userDto.isEnabled = user.isEnabled();

        return userDto;
    }
}
