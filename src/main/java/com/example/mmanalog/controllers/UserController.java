package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.ImageDto;
import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.models.Image;
import com.example.mmanalog.services.UserService;
import com.example.mmanalog.utilities.ImageUtility;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.FieldError;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;


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
    @PutMapping(path ="/{id}/photogallery/{galleryId}")
    public ResponseEntity<Object> assignPhotoGalleryToUser(@PathVariable("id") Long id, @PathVariable("galleryId") Long galleryId) {
    UserDto userDto = userService.assignPhotoGalleryToUser(id, galleryId);

    return ResponseEntity.ok().body(userDto);
    }

    @PutMapping(path = "/{userId}/images/{imageId}")
    public ResponseEntity<UserDto> addImageToUser(
            @PathVariable("userId") Long userId,
            @PathVariable("imageId") Long imageId) {
        UserDto userDto = userService.addImageToUser(userId, imageId);
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/{userId}/images")
    public ResponseEntity<List<ImageDto>> getUserImages(@PathVariable("userId") Long userId) {
        List<ImageDto> images = userService.getUserImages(userId);
        return ResponseEntity.ok(images);
    }

    @GetMapping("/{userId}/images/{imageId}")
    public ResponseEntity<Resource> getUserImage(@PathVariable("userId") Long userId, @PathVariable("imageId") Long imageId) {
        try {
            byte[] imageData = userService.getUserImage(userId, imageId);

            if (imageData != null && imageData.length > 0) {
                // You can modify this part to retrieve the image details based on your data model
                String imageName = "example.jpg";
                String imageType = "image/jpeg";

                // Process the image data using your ImageUtility class
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
                throw new RecordNotFoundException("Image data not found.");
            }
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            // Return an appropriate error response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
