package com.example.mmanalog.services;

import com.example.mmanalog.dtos.OutputDtos.ImageDto;
import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.models.Image;
import com.example.mmanalog.models.User;
import com.example.mmanalog.models.PhotoGallery;
import com.example.mmanalog.repositories.*;
import com.example.mmanalog.exceptions.InvalidPasswordException;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.exceptions.UserNotFoundException;
import com.example.mmanalog.utilities.ImageUtility;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.security.crypto.password.PasswordEncoder;

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
    private final ImageRepository imageRepository;

    public UserService(UserRepository userRepository, PhotoRepository photoRepository, ProjectFolderRepository projectFolderRepository, PhotoGalleryRepository photoGalleryRepository, ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
        this.projectFolderRepository = projectFolderRepository;
        this.photoGalleryRepository = photoGalleryRepository;
        this.imageRepository = imageRepository;
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
        userDto.photoGallery = user.getPhotoGallery();

        return userDto;
    }

    //Methods related to the relationship between entities
    public UserDto assignPhotoGalleryToUser(Long id, Long galleryId) {
        Optional<User> optionalUser = userRepository.findById(id);
        Optional<PhotoGallery> optionalPhotoGallery = photoGalleryRepository.findById(galleryId);

        if (optionalUser.isPresent() && optionalPhotoGallery.isPresent()) {
            User user = optionalUser.get();
            PhotoGallery photoGallery = optionalPhotoGallery.get();

            user.setPhotoGallery(photoGallery);
            userRepository.save(user);

            return transferUserToDto(user);
        } else {
            throw new RecordNotFoundException("No user or photo gallery found.");
        }
    }

    ///////
    public UserDto addImageToUser(Long userId, Long imageId) {
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
            throw new RecordNotFoundException("No user or image found.");
        }
    }

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
                images.remove(image); // Remove the image from the user's image list
                imageRepository.delete(image); // Delete the image from the repository
                userRepository.save(user); // Update the user entity
            } else {
                throw new RecordNotFoundException("No image found with ID: " + imageId);
            }
        } else {
            throw new UserNotFoundException("No user found with ID: " + userId);
        }
    }

    private ImageDto transferImageToDto(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(image.getId());
        imageDto.setName(image.getName());
        imageDto.setType(image.getType());
        imageDto.setImage(image.getImage());

        return imageDto;
    }
}


