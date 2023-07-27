package com.example.mmanalog.services;

import com.example.mmanalog.dtos.User.UserDto;
import com.example.mmanalog.models.Authority;
import com.example.mmanalog.models.Image;
import com.example.mmanalog.models.ProjectFolder;
import com.example.mmanalog.models.User;
import com.example.mmanalog.repositories.*;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.exceptions.UserNotFoundException;
import com.example.mmanalog.utilities.ImageUtility;
import com.example.mmanalog.utilities.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserService {

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public UserService(UserRepository userRepository, ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userList = new ArrayList<>();

        for (User user : users) {
            userList.add(transferUserToDto(user));
        }
        return userList;
    }

    public UserDto getUser(String username) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return transferUserToDto(user);
        } else {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("No user found with email: " + email);
        } else {
            return transferUserToDto(user);
        }
    }

    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        //userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User newUser = userRepository.save(transferToUser(userDto));
        return newUser.getUsername();
    }

    public void updateUser(String username, UserDto newUser) {
        if (!userRepository.existsById(username)) {
            throw new RecordNotFoundException("No user found with username: " + username);
        }
        User user = userRepository.findById(username).orElse(null);
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setEnabled(newUser.isEnabled());
        userRepository.save(user);
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }


    //*-----------------------------Authorities-----------------------------*//
    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException("No user found with username: " + username);
        User user = userRepository.findById(username).get();
        UserDto userDto = transferUserToDto(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException("No user found with username: " + username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException("No user found with username: " + username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    //*-----------------------------Transfers-----------------------------*//

    public User transferToUser(UserDto userDto) {
        User user = new User();

        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(userDto.isEnabled());
        user.setApikey(userDto.getApikey());

        return user;
    }

    public UserDto transferUserToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setEnabled(user.isEnabled());
        userDto.setAuthorities(user.getAuthorities());
        userDto.setApikey(user.getApikey());

        return userDto;
    }

    //*-----------------------------Methods related to the relationship between entities-----------------------------*//

    public UserDto assignImageToUser(String username, MultipartFile file) throws IOException {
        Optional<User> optionalUser = userRepository.findById(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Image image = Image.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .image(ImageUtility.compressImage(file.getBytes()))
                    .user(user)
                    .build();

            imageRepository.save(image);

            return transferUserToDto(user);
        } else {
            throw new RecordNotFoundException("No user found with username: " + username);
        }
    }

    public byte[] getUserImageByName(String username, String imageName) {
        Optional<User> optionalUser = userRepository.findById(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Image> images = user.getUserImages();

            Optional<Image> optionalImage = images.stream()
                    .filter(image -> image.getName().equals(imageName))
                    .findFirst();

            if (optionalImage.isPresent()) {
                Image image = optionalImage.get();
                return ImageUtility.decompressImage(image.getImage());
            } else {
                throw new RecordNotFoundException("No image found with name: " + imageName);
            }
        } else {
            throw new RecordNotFoundException("No user found with username: " + username);
        }
    }

    public void deleteUserImageByName(String username, String imageName) {
        Optional<User> optionalUser = userRepository.findById(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Image> images = user.getUserImages();

            Optional<Image> optionalImage = images.stream()
                    .filter(image -> image.getName().equals(imageName))
                    .findFirst();

            if (optionalImage.isPresent()) {
                Image image = optionalImage.get();
                images.remove(image);
                imageRepository.delete(image);
            } else {
                throw new RecordNotFoundException("No image found with name: " + imageName);
            }
        } else {
            throw new RecordNotFoundException("No project user found with username: " + username);
        }
    }

}


