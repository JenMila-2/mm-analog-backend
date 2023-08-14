package com.example.mmanalog.services;

import com.example.mmanalog.dtos.User.UserDto;
import com.example.mmanalog.exceptions.UserNotFoundException;
import com.example.mmanalog.models.User;
import com.example.mmanalog.repositories.UserRepository;
import com.example.mmanalog.utilities.RandomStringGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    RandomStringGenerator randomStringGenerator;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService;

    @Captor
    ArgumentCaptor<User> captor;

    User userTest1;
    User userTest2;
    UserDto userDto1;
    UserDto userDto2;

    @BeforeEach
    void setUp() {
        userTest1 = new User("UsernameTest1", "Barry Allen", "barry@mail.com", "UserPassword1", "testApikey1", true, null, null, null, null, null, null);

        userTest2 = new User("UsernameTest2", "Donna Troy", "donna@mail.com", "UserPassword2", "testApikey2", true, null, null, null, null, null, null);

        userDto1 = new UserDto("UsernameTest1", "Barry Allen", "barry@mail.com", "UserPassword1", "testApikey1", true, null);

        userDto2 = new UserDto("UsernameTest2", "Donna Troy", "donna@mail.com", "UserPassword2", "testApikey2", true, null);
    }

    @Test
    void getUsers() {

        List<User> userList = new ArrayList<>();
        userList.add(userTest1);
        userList.add(userTest2);

        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto1);
        userDtoList.add(userDto2);

        when(userRepository.findAll()).thenReturn(userList);

        List<UserDto> findUsers = userService.getUsers();

        assertEquals(userDtoList.get(0).getUsername(), findUsers.get(0).getUsername());
        assertEquals(userDtoList.get(0).getName(), findUsers.get(0).getName());
        assertEquals(userDtoList.get(0).getEmail(), findUsers.get(0).getEmail());
        assertEquals(userDtoList.get(0).getPassword(), findUsers.get(0).getPassword());
    }

    @Test
    void getUser() {
        //Arrange
        when(userRepository.findById("user1")).thenReturn(Optional.of(userTest1));

        //Act
        UserDto userDto = userService.getUser("user1");

        //Assert
        assertEquals("UsernameTest1", userDto.getUsername());
        assertEquals("UserPassword1", userDto.getPassword());
        assertEquals("barry@mail.com", userDto.getEmail());
    }

    @Test
    void getUser_UserNotFound() {
        // Arrange
        String nonExistingUsername = "NonExistingUser";
        when(userRepository.findById(nonExistingUsername)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.getUser(nonExistingUsername));

        // Verify that the userRepository's findById method was called with the correct username
        verify(userRepository).findById(nonExistingUsername);
    }

    @Test
    void getUser_UserExists() {
        // Arrange
        String username = "UsernameTest1";
        when(userRepository.findById(username)).thenReturn(Optional.of(userTest1));

        // Act
        UserDto userDto = userService.getUser(username);

        // Assert
        assertEquals(userTest1.getUsername(), userDto.getUsername());
        assertEquals(userTest1.getPassword(), userDto.getPassword());
        assertEquals(userTest1.getEmail(), userDto.getEmail());
    }

    @Test
    void getUserByEmail() {
        // Arrange
        String email = "test@email.com";
        User user = new User();
        user.setUsername("Username3");
        user.setEmail(email);
        // Mock the userRepository to return the user when findUserByEmail is called
        when(userRepository.findUserByEmail(email)).thenReturn(user);

        // Act
        UserDto expectedUser = userService.getUserByEmail(email);

        // Assert
        assertNotNull(expectedUser);
        assertEquals(user.getUsername(), expectedUser.getUsername());
        assertEquals(user.getEmail(), expectedUser.getEmail());

        // Verify that the userRepository's findUserByEmail method was called with the correct email
        verify(userRepository).findUserByEmail(email);
    }

    @Test
    public void getUserByEmail_NonExistingUser() {
        // Arrange
        String email = "nonexisting@email.com";

        when(userRepository.findUserByEmail(email)).thenReturn(null);

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail(email));

        // Verify that the userRepository's findUserByEmail method was called with the correct email
        verify(userRepository).findUserByEmail(email);
    }

    @Test
    void deleteUser() {
        //Act
        userService.deleteUser("UsernameTest2");

        //Assert
        verify(userRepository).deleteById("UsernameTest2");

        assertFalse(userRepository.findById(userTest2.getUsername()).isPresent());
    }
}