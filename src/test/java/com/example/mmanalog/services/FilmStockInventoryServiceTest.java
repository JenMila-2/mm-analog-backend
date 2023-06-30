package com.example.mmanalog.services;

import com.example.mmanalog.dtos.InputDtos.FilmStockInventoryInputDto;
import com.example.mmanalog.dtos.OutputDtos.FilmStockInventoryDto;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.exceptions.UserNotFoundException;
import com.example.mmanalog.models.FilmStockInventory;
import com.example.mmanalog.models.User;
import com.example.mmanalog.repositories.FilmStockInventoryRepository;
import com.example.mmanalog.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmStockInventoryServiceTest {

    @Mock
    FilmStockInventoryRepository filmStockInventoryRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    UserService userService;

    @InjectMocks
    FilmStockInventoryService filmStockInventoryService;

    @Captor
    ArgumentCaptor<FilmStockInventory> captor;

    FilmStockInventory filmStockInventory1;
    FilmStockInventory filmStockInventory2;
    FilmStockInventory filmStockInventory3;
    User user1;
    User user2;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setUsername("Username1Test");
        user1.setName("Barry Allen");
        user1.setEmail("barry@mail.com");
        user1.setPassword("UserPassword1");

        user2 = new User();
        user2.setUsername("Username2Test");
        user2.setName("Donna Troy");
        user2.setEmail("donna@mail.com");
        user2.setPassword("UserPassword2");

        filmStockInventory1 = new FilmStockInventory();
        filmStockInventory1.setId(1L);
        filmStockInventory1.setFilmStockName("Kodak Portra 400");
        filmStockInventory1.setRemainingRolls(2);
        filmStockInventory1.setBrand("Kodak");
        filmStockInventory1.setStock("Portra 400");
        filmStockInventory1.setFormat("35mm");
        filmStockInventory1.setIso(400);
        filmStockInventory1.setDevelopmentProcess("C-41 Color");
        filmStockInventory1.setStorage("Freezer");
        filmStockInventory1.setRollsShot(6);
        filmStockInventory1.setFilmExpirationDate(LocalDate.parse("2025-05-01"));

        filmStockInventory2 = new FilmStockInventory();
        filmStockInventory2.setId(2L);
        filmStockInventory2.setFilmStockName("Lomo Lady Grey 400");
        filmStockInventory2.setRemainingRolls(6);
        filmStockInventory2.setBrand("Lomography");
        filmStockInventory2.setStock("Lady Grey 400");
        filmStockInventory2.setFormat("35mm");
        filmStockInventory2.setIso(400);
        filmStockInventory2.setDevelopmentProcess("Black & White");
        filmStockInventory2.setStorage("Freezer");
        filmStockInventory2.setRollsShot(2);
        filmStockInventory2.setFilmExpirationDate(LocalDate.parse("2026-01-01"));

        filmStockInventory3 = new FilmStockInventory();
        filmStockInventory3.setId(3L);
        filmStockInventory3.setFilmStockName("Kodak Gold");
        filmStockInventory3.setRemainingRolls(10);
        filmStockInventory3.setBrand("Kodak");
        filmStockInventory3.setStock("Kodak Gold 200");
        filmStockInventory3.setFormat("35mm");
        filmStockInventory3.setIso(200);
        filmStockInventory3.setDevelopmentProcess("C-41 Color");
        filmStockInventory3.setStorage("Freezer");
        filmStockInventory3.setRollsShot(6);
        filmStockInventory3.setFilmExpirationDate(LocalDate.parse("2025-04-10"));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    //@Disabled
    void getFilmStockInventories() {

        //Arrange
        when(filmStockInventoryRepository.findAll()).thenReturn(List.of(filmStockInventory1, filmStockInventory2));

        //Act
        List<FilmStockInventory> inventories = filmStockInventoryRepository.findAll();
        List<FilmStockInventoryDto> dtos = filmStockInventoryService.getFilmStockInventories();

        //Assert
        assertEquals(inventories.get(0).getFilmStockName(), dtos.get(0).getFilmStockName());
        assertEquals(inventories.get(0).getRemainingRolls(), dtos.get(0).getRemainingRolls());
        assertEquals(inventories.get(0).getBrand(), dtos.get(0).getBrand());
        assertEquals(inventories.get(0).getStock(), dtos.get(0).getStock());
        assertEquals(inventories.get(0).getFormat(), dtos.get(0).getFormat());
        assertEquals(inventories.get(0).getIso(), dtos.get(0).getIso());
        assertEquals(inventories.get(0).getDevelopmentProcess(), dtos.get(0).getDevelopmentProcess());
        assertEquals(inventories.get(0).getStorage(), dtos.get(0).getStorage());
        assertEquals(inventories.get(0).getRollsShot(), dtos.get(0).getRollsShot());
        assertEquals(inventories.get(0).getFilmExpirationDate(), dtos.get(0).getFilmExpirationDate());
    }

    @Test
    void getFilmStockInventory() {
        //Arrange
        when(filmStockInventoryRepository.findById(2L)).thenReturn(Optional.of(filmStockInventory2));

        //Act
        FilmStockInventoryDto filmStockInventoryDto = filmStockInventoryService.getFilmStockInventory(2L);

        //Assert
        assertEquals(filmStockInventory2.getFilmStockName(), filmStockInventoryDto.filmStockName);
    }

    @Test
    void getFilmStockInventoryThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> filmStockInventoryService.getFilmStockInventory(null));
    }

    @Test
    void createFilmStockInventory() {

        // Arrange
        FilmStockInventoryInputDto filmStockInventoryInputDto = new FilmStockInventoryInputDto();
        filmStockInventoryInputDto.setFilmStockName("Ilford HP5Plus");
        filmStockInventoryInputDto.setRemainingRolls(6);
        filmStockInventoryInputDto.setBrand("Ilford");
        filmStockInventoryInputDto.setStock("HP5Plus");
        filmStockInventoryInputDto.setFormat("35mm");
        filmStockInventoryInputDto.setIso(400);
        filmStockInventoryInputDto.setDevelopmentProcess("Black & White");
        filmStockInventoryInputDto.setStorage("Freezer");
        filmStockInventoryInputDto.setRollsShot(5);
        filmStockInventoryInputDto.setFilmExpirationDate(LocalDate.parse("2025-05-01"));

        FilmStockInventory filmStockInventory = new FilmStockInventory();
        filmStockInventory.setFilmStockName("Ilford HP5Plus");
        filmStockInventory.setRemainingRolls(6);
        filmStockInventory.setBrand("Ilford");
        filmStockInventory.setStock("HP5Plus");
        filmStockInventory.setFormat("35mm");
        filmStockInventory.setIso(400);
        filmStockInventory.setDevelopmentProcess("Black & White");
        filmStockInventory.setStorage("Freezer");
        filmStockInventory.setRollsShot(5);
        filmStockInventory.setFilmExpirationDate(LocalDate.parse("2025-05-01"));

        when(filmStockInventoryRepository.save(any(FilmStockInventory.class))).thenReturn(filmStockInventory);

        // Act
        FilmStockInventoryDto resultDto = filmStockInventoryService.createFilmStockInventory(filmStockInventoryInputDto);

        // Assert
        verify(filmStockInventoryRepository).save(captor.capture());
        FilmStockInventory savedFilmStockInventory = captor.getValue();

        assertEquals(filmStockInventoryInputDto.getFilmStockName(), savedFilmStockInventory.getFilmStockName());
        assertEquals(filmStockInventoryInputDto.getRemainingRolls(), savedFilmStockInventory.getRemainingRolls());
        assertEquals(filmStockInventoryInputDto.getBrand(), savedFilmStockInventory.getBrand());
        assertEquals(filmStockInventoryInputDto.getStock(), savedFilmStockInventory.getStock());
        assertEquals(filmStockInventoryInputDto.getFormat(), savedFilmStockInventory.getFormat());
        assertEquals(filmStockInventoryInputDto.getIso(), savedFilmStockInventory.getIso());
        assertEquals(filmStockInventoryInputDto.getDevelopmentProcess(), savedFilmStockInventory.getDevelopmentProcess());
        assertEquals(filmStockInventoryInputDto.getStorage(), savedFilmStockInventory.getStorage());
        assertEquals(filmStockInventoryInputDto.getRollsShot(), savedFilmStockInventory.getRollsShot());
        assertEquals(filmStockInventoryInputDto.getFilmExpirationDate(), savedFilmStockInventory.getFilmExpirationDate());

        assertEquals(filmStockInventory.getFilmStockName(), resultDto.getFilmStockName());
        assertEquals(filmStockInventory.getRemainingRolls(), resultDto.getRemainingRolls());
        assertEquals(filmStockInventory.getBrand(), resultDto.getBrand());
        assertEquals(filmStockInventory.getStock(), resultDto.getStock());
        assertEquals(filmStockInventory.getFormat(), resultDto.getFormat());
        assertEquals(filmStockInventory.getIso(), resultDto.getIso());
        assertEquals(filmStockInventory.getDevelopmentProcess(), resultDto.getDevelopmentProcess());
        assertEquals(filmStockInventory.getStorage(), resultDto.getStorage());
        assertEquals(filmStockInventory.getRollsShot(), resultDto.getRollsShot());
        assertEquals(filmStockInventory.getFilmExpirationDate(), resultDto.getFilmExpirationDate());
    }

    @Test
    void updateFilmStockInventory() {
        //Arrange
        FilmStockInventoryInputDto filmStockInventoryInputDto = new FilmStockInventoryInputDto();
        filmStockInventoryInputDto.setFilmStockName("Kodak Ultra Max");
        filmStockInventoryInputDto.setStock("Ultra Max");
        filmStockInventoryInputDto.setIso(400);
        FilmStockInventory filmStockInventory = new FilmStockInventory();
        filmStockInventory.setFilmStockName("Kodak Ultra Max");
        filmStockInventory.setStock("Ultra Max");
        filmStockInventory.setIso(400);

        //Act
        when(filmStockInventoryRepository.findById(3L)).thenReturn(Optional.of(filmStockInventory3));

        filmStockInventoryService.updateFilmStockInventory(3L, filmStockInventoryInputDto);

        verify(filmStockInventoryRepository, times(1)).save(captor.capture());

        FilmStockInventory captured = captor.getValue();

        //Assert
        assertEquals(filmStockInventory.getFilmStockName(), captured.getFilmStockName());
        assertEquals(filmStockInventory.getStock(), captured.getStock());
        assertEquals(filmStockInventory.getIso(), captured.getIso());
    }

    @Test
    void updateFilmStockInventoryThrowsExceptionTest() {
        // Arrange
        Long nonExistingId = 999L;
        FilmStockInventoryInputDto updatedFilmStockInventory = new FilmStockInventoryInputDto();
        updatedFilmStockInventory.setFilmStockName("Updated Film Stock");

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () ->
                filmStockInventoryService.updateFilmStockInventory(nonExistingId, updatedFilmStockInventory));
    }

    @Test
    void deleteFilmStockInventory() {

        //Act
        filmStockInventoryService.deleteFilmStockInventory(3L);

        //Assert
        verify(filmStockInventoryRepository).deleteById(3L);

        assertFalse(filmStockInventoryRepository.findById(filmStockInventory3.getId()).isPresent());
    }

    @Test
    void createFilmStockInventoryForUser_userExists() {
        // Arrange
        String username = "Username1Test";
        FilmStockInventoryInputDto inputDto = new FilmStockInventoryInputDto();
        inputDto.setFilmStockName("Kodak Portra 400");

        User user = new User();
        user.setUsername(username);

        when(userRepository.findById(username)).thenReturn(Optional.of(user));

        FilmStockInventory filmStockInventory = new FilmStockInventory();
        filmStockInventory.setUser(user);
        filmStockInventory.setFilmStockName("Kodak Portra 400");

        when(filmStockInventoryRepository.save(any(FilmStockInventory.class))).thenReturn(filmStockInventory);

        // Act
        FilmStockInventoryDto resultDto = filmStockInventoryService.createFilmStockInventoryForUser(username, inputDto);

        // Assert
        verify(userRepository).findById(username);
        verify(filmStockInventoryRepository).save(captor.capture());
        FilmStockInventory savedFilmStockInventory = captor.getValue();

        assertEquals(user, savedFilmStockInventory.getUser());
        assertEquals(inputDto.getFilmStockName(), savedFilmStockInventory.getFilmStockName());

        assertEquals(filmStockInventory.getFilmStockName(), resultDto.getFilmStockName());
    }

    @Test
    void createFilmStockInventoryForUser_userNotFound() {
        // Arrange
        String username = "NonExistingUser";
        FilmStockInventoryInputDto inputDto = new FilmStockInventoryInputDto();
        inputDto.setFilmStockName("Kodak Portra 400");

        when(userRepository.findById(username)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> filmStockInventoryService.createFilmStockInventoryForUser(username, inputDto));

        verify(userRepository).findById(username);
        verifyNoInteractions(filmStockInventoryRepository);
    }
}