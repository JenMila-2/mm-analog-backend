package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.User.UserDto;
import com.example.mmanalog.services.UserService;
import com.example.mmanalog.exceptions.BadRequestException;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;

import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
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

    @GetMapping(path = "/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable("username") String username) {

    UserDto user = userService.getUser(username);

    return ResponseEntity.ok().body(user);
    }

    @GetMapping(path = "emails/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {

    return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

    String newUsername = userService.createUser(userDto);
    userService.addAuthority(newUsername, "ROLE_USER");

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
            .buildAndExpand(newUsername).toUri();

    return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username, @Valid @RequestBody UserDto updatedUser) {

    userService.updateUser(username, updatedUser);

    return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {

        userService.deleteUser(username);

        return ResponseEntity.noContent().build();
    }
    //*-----------------------------Authorities-----------------------------*//
    @GetMapping(path = "/{username}/authorities")
    public ResponseEntity<Object> getAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }

    @PostMapping(path = "/{username}/authorities")
    public ResponseEntity<Object> addAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) throws BadRequestException {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(path = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> removeAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }


    //*-----------------------------Methods related to the relationship between entities-----------------------------*//

}
