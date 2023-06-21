package com.example.mmanalog.services;

import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.exceptions.InvalidPasswordException;
import com.example.mmanalog.models.Authority;
import com.example.mmanalog.models.Image;
import com.example.mmanalog.models.User;
import com.example.mmanalog.repositories.*;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.exceptions.UserNotFoundException;
import com.example.mmanalog.utilities.RandomStringGenerator;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class UserService {

    @Lazy
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
            throw new UserNotFoundException("No user found with username: " + username);
        }
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("No user found with email: " + email);
        } else {
            return transferUserToDto(user);
        }
    }

    public String createUser(UserDto dtoUser) {
        String password = dtoUser.getPassword();
        if (validatePassword(password)) {
            String randomString = RandomStringGenerator.generateAlphaNumeric(20);
            dtoUser.setApikey(randomString);
            dtoUser.setPassword(passwordEncoder.encode(dtoUser.getPassword()));
            User newUser = userRepository.save(transferToUser(dtoUser));
            return newUser.getUsername();
        } else {
            throw new InvalidPasswordException("Invalid password. Your password must contain minimal 6 characters, 1 uppercase letter, 1 lowercase letter, 1 special character. Make sure it does not contain white spaces.");
        }
    }

    public Boolean validatePassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*?=])(?=\\S+$).{6,}$");
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDto updatedUser) {
        String password = updatedUser.getPassword();
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        if (validatePassword(password)) {
            User user = userRepository.findById(username).get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

            userRepository.save(user);
        } else {
            throw new UserNotFoundException("No user found with username: " + username);
        }
    }

    //// ***** Authorities **** ////
    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = transferUserToDto(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UserNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    //// ***** Transfers **** ////
    public User transferToUser(UserDto userDto) {

        User user = new User();

        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.isEnabled());

        return user;
    }

    public UserDto transferUserToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.id = user.getId();
        userDto.name = user.getName();
        userDto.username = user.getUsername();
        userDto.email = user.getEmail();
        userDto.password = user.getPassword();
        userDto.enabled = user.isEnabled();

        return userDto;
    }

    //// **** Methods related to the relationship between entities **** ////
    //// **** Relationship between image and user **** ////
    public UserDto assignImageToUser(String username, Long imageId) {
        Optional<User> optionalUser = userRepository.findById(username);
        Optional<Image> optionalImage = imageRepository.findById(imageId);

        if (optionalUser.isPresent() && optionalImage.isPresent()) {
            User user = optionalUser.get();
            Image image = optionalImage.get();

            image.setUser(user);
            user.getUserImages().add(image);

            userRepository.save(user);

            return transferUserToDto(user);
        } else {
            throw new RecordNotFoundException("Username: " + username + " or image with id: " + imageId + " not found");
        }
    }

    public byte[] getUserImage(String username, Long imageId) {
        Optional<User> optionalUser = userRepository.findById(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Image> images = user.getUserImages();

            Optional<Image> optionalImage = images.stream()
                    .filter(image -> image.getId().equals(imageId))
                    .findFirst();

            if (optionalImage.isPresent()) {
                Image image = optionalImage.get();
                return image.getImage();
            } else {
                throw new RecordNotFoundException("No image found with id: " + imageId);
            }
        } else {
            throw new UserNotFoundException("No user found with username: " + username);
        }
    }

    public void deleteUserImage(String username, Long imageId) {
        Optional<User> optionalUser = userRepository.findById(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Image> images = user.getUserImages();

            Optional<Image> optionalImage = images.stream()
                    .filter(image -> image.getId().equals(imageId))
                    .findFirst();

            if (optionalImage.isPresent()) {
                Image image = optionalImage.get();
                images.remove(image);
                imageRepository.delete(image);
                userRepository.save(user);
            } else {
                throw new RecordNotFoundException("No image found with id: " + imageId);
            }
        } else {
            throw new UserNotFoundException("No user found with username: " + username);
        }
    }

    //// *** Specials *** ////
    //Method below only returns the image data and not the actual images//
    public List<byte[]> getAllUserImages(String username) {
        Optional<User> optionalUser = userRepository.findById(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Image> images = user.getUserImages();

            List<byte[]> imageList = new ArrayList<>();
            for (Image image : images) {
                imageList.add(image.getImage());
            }
            return imageList;
        } else {
            throw new RecordNotFoundException("No user found with id: " + username);
        }
    }

    //Method to retrieve an Array of the image id's//
    public List<Long> getUserImageIds(String username) {
        Optional<User> optionalUser = userRepository.findById(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Image> images = user.getUserImages();

            List<Long> imageIds = new ArrayList<>();
            for (Image image : images) {
                imageIds.add(image.getId());
            }
            return imageIds;
        } else {
            throw new UserNotFoundException("No user found with id: " + username);
        }
    }
}


