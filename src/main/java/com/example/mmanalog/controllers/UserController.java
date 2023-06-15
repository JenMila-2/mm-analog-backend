package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.exceptions.BadRequestException;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.exceptions.UserNotFoundException;
import com.example.mmanalog.services.UserService;
import com.example.mmanalog.utilities.ImageUtility;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

//
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

private final UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}

    @GetMapping(path = "")
    public ResponseEntity<List<UserDto>> getUsers() {

        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {

    UserDto user = userService.getUserById(id);

    return ResponseEntity.ok().body(user);
    }

    @GetMapping(path = "emails/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {

    return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PostMapping(path = "")
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {

    UserDto dtoUser = userService.addUser(userDto);

    return ResponseEntity.created(null).body(dtoUser);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {

    userService.deleteUser(id);

    return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto newUser) {

    UserDto userDto = userService.updateUser(id, newUser);

    return ResponseEntity.ok().body(userDto);
    }

    // *** Methods related to the relationship between entities ***
    @PutMapping(path = "/{userId}/images/{imageId}")
    public ResponseEntity<UserDto> assignImageToUser(
            @PathVariable("userId") Long userId,
            @PathVariable("imageId") Long imageId) {
        UserDto userDto = userService.assignImageToUser(userId, imageId);
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping(path = "/{userId}/images/{imageId}")
    public ResponseEntity<Resource> getUserImage(@PathVariable("userId") Long userId, @PathVariable("imageId") Long imageId) {
        byte[] imageData = userService.getUserImage(userId, imageId);

        if (imageData != null && imageData.length > 0) {
            String imageName = "example.jpg";
            String imageType = "image/jpeg";

            // Process the image data using the ImageUtility class
            byte[] processedImageData = ImageUtility.decompressImage(imageData);

            ByteArrayResource resource = new ByteArrayResource(processedImageData);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(imageType));
            headers.setContentLength(resource.contentLength());
            headers.setContentDisposition(ContentDisposition.builder("inline").filename(imageName).build());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } else {
            throw new BadRequestException("Error. Image or user not found.");
        }
    }

    @DeleteMapping(path = "/{userId}/images/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable("userId") Long userId, @PathVariable("imageId") Long imageId) {
        try {
            userService.deleteUserImage(userId, imageId);
            return ResponseEntity.ok("Image deleted successfully.");
        } catch (UserNotFoundException e) {
            throw new RecordNotFoundException("User not found with id: " + userId);
        } catch (RecordNotFoundException e) {
            throw new RecordNotFoundException("Image with id: " + imageId + " does not exist or has already been deleted.");
        }
    }

    ////*** Specials ***////

    //Method below only returns the image data and not the actual images
    @GetMapping(path = "/{userId}/images")
    public ResponseEntity<List<byte[]>> getAllUserImages(@PathVariable("userId") Long userId) {
        try {
            List<byte[]> images = userService.getAllUserImages(userId);

            if (!images.isEmpty()) {
                return new ResponseEntity<>(images, HttpStatus.OK);
            } else {
                throw new RecordNotFoundException("No images found for user with id. " + userId);
            }
        } catch (Exception e) {
            throw new BadRequestException("Error while retrieving data.");
        }
    }

    //Method to retrieve the id's of the images//
    @GetMapping(path = "/{userId}/imageIds")
    public ResponseEntity<List<Long>> getUserImageIds(@PathVariable("userId") Long userId) {
        try {
            List<Long> imageIds = userService.getUserImageIds(userId);

            if (!imageIds.isEmpty()) {
                return ResponseEntity.ok(imageIds);
            } else {
                throw new RecordNotFoundException("No image IDs found for the user.");
            }
        } catch (Exception e) {
            throw new BadRequestException("Error while retrieving image IDs.");
        }
    }
}
