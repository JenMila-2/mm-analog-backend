package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.services.UserService;
import com.example.mmanalog.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

private final UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}

@PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto, BindingResult br) {
    if (br.hasFieldErrors()) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : br.getFieldErrors()) {
            sb.append(fe.getField() + ": ");
            sb.append(fe.getDefaultMessage());
            sb.append("`n");
        }
        return ResponseEntity.badRequest().body(sb.toString());
    }
    else {
        Long newId = userService.createUser(userDto);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + newId).toUriString());

        return ResponseEntity.created(uri).body(newId);
    }
}
}
