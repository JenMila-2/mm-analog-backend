package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    //Method to assign a gallery to the user
    @PutMapping(path ="/{id}/photogallery/{galleryId}")
    public ResponseEntity<Object> assignPhotoGalleryToUser(@PathVariable("id") Long id, @PathVariable("galleryId") Long galleryId) {
    UserDto userDto = userService.assignPhotoGalleryToUser(id, galleryId);

    return ResponseEntity.ok().body(userDto);
    }
}
