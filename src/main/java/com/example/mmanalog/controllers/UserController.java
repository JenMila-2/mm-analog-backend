package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.repositories.UserRepository;
import com.example.mmanalog.models.User;
import com.example.mmanalog.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.mmanalog.exceptions.UserNotFoundException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

public UserController(UserService service) {
    this.service = service;
}

}
