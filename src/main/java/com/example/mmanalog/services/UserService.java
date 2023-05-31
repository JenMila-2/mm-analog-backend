package com.example.mmanalog.services;

import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.dtos.OutputDtos.PhotoDto;
import com.example.mmanalog.dtos.OutputDtos.PhotoGalleryDto;
import com.example.mmanalog.models.User;
import com.example.mmanalog.models.Photo;
import com.example.mmanalog.models.PhotoGallery;
import com.example.mmanalog.models.ProjectFolder;
import com.example.mmanalog.repositories.UserRepository;
import com.example.mmanalog.repositories.PhotoRepository;
import com.example.mmanalog.repositories.PhotoGalleryRepository;
import com.example.mmanalog.repositories.ProjectFolderRepository;
import com.example.mmanalog.exceptions.InvalidPasswordException;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    //private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;
    private final ProjectFolderRepository projectFolderRepository;
    private final PhotoGalleryRepository photoGalleryRepository;

    public UserService(UserRepository userRepository, PhotoRepository photoRepository, ProjectFolderRepository projectFolderRepository, PhotoGalleryRepository photoGalleryRepository) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
        this.projectFolderRepository = projectFolderRepository;
        this.photoGalleryRepository = photoGalleryRepository;
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
            throw new UserNotFoundException("No user found with id: " + id);
        }
    }

    public List<UserDto> getAllUsersByEmail(String email) {
        List<User> userEmailList = userRepository.findAllUsersByEmail(email);
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : userEmailList) {
            UserDto userDto = transferUserToDto(user);
            userDtos.add(userDto);
        } return userDtos;
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new RecordNotFoundException("No user found with email: " + email);
        } else {
            return transferUserToDto(user);
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
            throw new UserNotFoundException("No user found with id: " + id);
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
        userDto.enabled = user.isEnabled();

        return userDto;
    }

    //Methods related to the relationship between entities
    public UserDto assignPhotoGalleryToUser(Long id, Long galleryId) {
        var optionalUser = userRepository.findById(id);
        var optionalPhotoGallery = photoGalleryRepository.findById(galleryId);

        if (optionalUser.isPresent() && optionalUser.isPresent()) {
            User user = optionalUser.get();
            PhotoGallery photoGallery = optionalPhotoGallery.get();

            user.setPhotoGallery(photoGallery);
            userRepository.save(user);

            return transferUserToDto(user);
        } else {
            throw new RecordNotFoundException("No user or photo gallery found.");
        }
    }
}
