package com.example.mmanalog.services;

import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.models.Image;
import com.example.mmanalog.models.User;
import com.example.mmanalog.repositories.*;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    //@Autowired
    //@Lazy
    //private PasswordEncoder passwordEncoder;

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

    public UserDto getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return transferUserToDto(user);
        } else {
            throw new UserNotFoundException("No user found with id: " + id);
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

    public UserDto getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("No user found with username: " + username);
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
            User user = userOptional.get();

            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());

            User returnUser = userRepository.save(user);

            return transferUserToDto(returnUser);

        } else {
            throw new UserNotFoundException("No user found with id: " + id);
        }
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
    public UserDto assignImageToUser(Long userId, Long imageId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Image> optionalImage = imageRepository.findById(imageId);

        if (optionalUser.isPresent() && optionalImage.isPresent()) {
            User user = optionalUser.get();
            Image image = optionalImage.get();

            image.setUser(user);
            user.getUserImages().add(image);

            userRepository.save(user);

            return transferUserToDto(user);
        } else {
            throw new RecordNotFoundException("User id: " + userId + " or image id: " + imageId + " not found");
        }
    }

    public byte[] getUserImage(Long userId, Long imageId) {
        Optional<User> optionalUser = userRepository.findById(userId);

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
            throw new UserNotFoundException("No user found with id: " + userId);
        }
    }

    public void deleteUserImage(Long userId, Long imageId) {
        Optional<User> optionalUser = userRepository.findById(userId);

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
            throw new UserNotFoundException("No user found with id: " + userId);
        }
    }

    //// *** Specials *** ////
    //Method below only returns the image data and not the actual images//
    public List<byte[]> getAllUserImages(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Image> images = user.getUserImages();

            List<byte[]> imageList = new ArrayList<>();
            for (Image image : images) {
                imageList.add(image.getImage());
            }
            return imageList;
        } else {
            throw new RecordNotFoundException("No user found with id: " + userId);
        }
    }

    //Method to retrieve an Array of the image id's//
    public List<Long> getUserImageIds(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Image> images = user.getUserImages();

            List<Long> imageIds = new ArrayList<>();
            for (Image image : images) {
                imageIds.add(image.getId());
            }
            return imageIds;
        } else {
            throw new UserNotFoundException("No user found with id: " + userId);
        }
    }
}


