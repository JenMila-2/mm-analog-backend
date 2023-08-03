package com.example.mmanalog.services;

import com.example.mmanalog.dtos.OutputDtos.FilmStockInventoryDto;
import com.example.mmanalog.dtos.InputDtos.FilmStockInventoryInputDto;
import com.example.mmanalog.models.FilmStockInventory;
import com.example.mmanalog.models.User;
import com.example.mmanalog.repositories.UserRepository;
import com.example.mmanalog.repositories.FilmStockInventoryRepository;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class FilmStockInventoryService {

    private final FilmStockInventoryRepository filmStockInventoryRepository;
    private final UserRepository userRepository;

    public FilmStockInventoryService(FilmStockInventoryRepository filmStockInventoryRepository, UserRepository userRepository) {
        this.filmStockInventoryRepository = filmStockInventoryRepository;
        this.userRepository = userRepository;
    }

    public List<FilmStockInventoryDto> getFilmStockInventories() {
        List<FilmStockInventory> filmStockInventories = filmStockInventoryRepository.findAll();
        List<FilmStockInventoryDto> filmStockInventoryList = new ArrayList<>();

        for (FilmStockInventory filmStockInventory : filmStockInventories) {
            filmStockInventoryList.add(transferFilmStockInventoryToDto(filmStockInventory));
        }
        return filmStockInventoryList;
    }

    public FilmStockInventoryDto getFilmStockInventory(Long id) {
        Optional<FilmStockInventory> filmStockInventoryOptional = filmStockInventoryRepository.findById(id);
        if (filmStockInventoryOptional.isPresent()) {
            FilmStockInventory filmStockInventory = filmStockInventoryOptional.get();
            return transferFilmStockInventoryToDto((filmStockInventory));
        } else {
            throw new RecordNotFoundException("No film stock inventory found with id: " + id);
        }
    }

    public List<FilmStockInventoryDto> getAllFilmStockInventoriesByUser(User user) {
        List<FilmStockInventory> filmStockInventoryList = filmStockInventoryRepository.findFilmStockInventoryByUser(user);
        List<FilmStockInventoryDto> filmStockInventoryDtoList = new ArrayList<>();
        for(FilmStockInventory filmStockInventory : filmStockInventoryList) {
            FilmStockInventoryDto dto = transferFilmStockInventoryToDto(filmStockInventory);
            filmStockInventoryDtoList.add(dto);
        }
        return filmStockInventoryDtoList;
    }

    public FilmStockInventoryDto createFilmStockInventory(FilmStockInventoryInputDto filmStockInventoryInputDto) {
        FilmStockInventory filmStockInventory = transferToFilmStockInventory(filmStockInventoryInputDto);
        filmStockInventoryRepository.save(filmStockInventory);

        return transferFilmStockInventoryToDto(filmStockInventory);
    }

    public void updateFilmStockInventory(Long id, FilmStockInventoryDto updatedFilmStockInventory) {

        if (!filmStockInventoryRepository.existsById(id)) {
            throw new RecordNotFoundException("No film stock inventory found with id: " + id);
        }
        FilmStockInventory storedFilmStockInventory = filmStockInventoryRepository.findById(id).orElse(null);
        storedFilmStockInventory.setFilmStockName(updatedFilmStockInventory.getFilmStockName());
        storedFilmStockInventory.setRemainingRolls(updatedFilmStockInventory.getRemainingRolls());
        storedFilmStockInventory.setBrand(updatedFilmStockInventory.getBrand());
        storedFilmStockInventory.setStock(updatedFilmStockInventory.getStock());
        storedFilmStockInventory.setFormat(updatedFilmStockInventory.getFormat());
        storedFilmStockInventory.setIso(updatedFilmStockInventory.getIso());
        storedFilmStockInventory.setDevelopmentProcess(updatedFilmStockInventory.getDevelopmentProcess());
        storedFilmStockInventory.setStorage(updatedFilmStockInventory.getStorage());
        storedFilmStockInventory.setRollsShot(updatedFilmStockInventory.getRollsShot());
        storedFilmStockInventory.setFilmExpirationDate(updatedFilmStockInventory.getFilmExpirationDate());
        storedFilmStockInventory.setUser(updatedFilmStockInventory.getUser());

        filmStockInventoryRepository.save(storedFilmStockInventory);
    }

    public void deleteFilmStockInventory(@RequestBody Long id) {
        filmStockInventoryRepository.deleteById(id);
    }

    //*---------------------------------Transfers---------------------------------*//

    public FilmStockInventory transferToFilmStockInventory(FilmStockInventoryInputDto filmStockInventoryInputDto) {
        FilmStockInventory filmStockInventory = new FilmStockInventory();

        filmStockInventory.setId(filmStockInventoryInputDto.getId());
        filmStockInventory.setFilmStockName(filmStockInventoryInputDto.getFilmStockName());
        filmStockInventory.setRemainingRolls(filmStockInventoryInputDto.getRemainingRolls());
        filmStockInventory.setBrand(filmStockInventoryInputDto.getBrand());
        filmStockInventory.setStock(filmStockInventoryInputDto.getStock());
        filmStockInventory.setFormat(filmStockInventoryInputDto.getFormat());
        filmStockInventory.setIso(filmStockInventoryInputDto.getIso());
        filmStockInventory.setDevelopmentProcess(filmStockInventoryInputDto.getDevelopmentProcess());
        filmStockInventory.setStorage(filmStockInventoryInputDto.getStorage());
        filmStockInventory.setRollsShot(filmStockInventoryInputDto.getRollsShot());
        filmStockInventory.setFilmExpirationDate(filmStockInventoryInputDto.getFilmExpirationDate());

        return filmStockInventory;
    }

    public FilmStockInventoryDto transferFilmStockInventoryToDto(FilmStockInventory filmStockInventory) {
        FilmStockInventoryDto filmStockInventoryDto = new FilmStockInventoryDto();

        filmStockInventoryDto.setId(filmStockInventory.getId());
        filmStockInventoryDto.setFilmStockName(filmStockInventory.getFilmStockName());
        filmStockInventoryDto.setRemainingRolls(filmStockInventory.getRemainingRolls());
        filmStockInventoryDto.setBrand(filmStockInventory.getBrand());
        filmStockInventoryDto.setStock(filmStockInventory.getStock());
        filmStockInventoryDto.setFormat(filmStockInventory.getFormat());
        filmStockInventoryDto.setIso(filmStockInventory.getIso());
        filmStockInventoryDto.setDevelopmentProcess(filmStockInventory.getDevelopmentProcess());
        filmStockInventoryDto.setStorage(filmStockInventory.getStorage());
        filmStockInventoryDto.setRollsShot(filmStockInventory.getRollsShot());
        filmStockInventoryDto.setFilmExpirationDate(filmStockInventory.getFilmExpirationDate());
        filmStockInventoryDto.setUser(filmStockInventory.getUser());

        return filmStockInventoryDto;
    }

    //*-----------------------------Methods related to the relationship between entities-----------------------------*//

    public FilmStockInventoryDto createFilmStockInventoryForUser(String username, FilmStockInventoryInputDto filmStockInventoryInputDto) {
        Optional<User> userOptional = userRepository.findById(username);

        if(userOptional.isPresent()) {
            User user = userOptional.get();

            FilmStockInventory filmStockInventory = new FilmStockInventory();
            filmStockInventory.setUser(user);
            filmStockInventory.setFilmStockName(filmStockInventoryInputDto.getFilmStockName());
            filmStockInventory.setRemainingRolls(filmStockInventoryInputDto.getRemainingRolls());
            filmStockInventory.setBrand(filmStockInventoryInputDto.getBrand());
            filmStockInventory.setStock(filmStockInventoryInputDto.getStock());
            filmStockInventory.setFormat(filmStockInventoryInputDto.getFormat());
            filmStockInventory.setIso(filmStockInventoryInputDto.getIso());
            filmStockInventory.setDevelopmentProcess(filmStockInventoryInputDto.getDevelopmentProcess());
            filmStockInventory.setStorage(filmStockInventoryInputDto.getStorage());
            filmStockInventory.setRollsShot(filmStockInventoryInputDto.getRollsShot());
            filmStockInventory.setFilmExpirationDate(filmStockInventoryInputDto.getFilmExpirationDate());

            filmStockInventoryRepository.save(filmStockInventory);

            return transferFilmStockInventoryToDto(filmStockInventory);
        } else {
            throw new UserNotFoundException("No user found with username: " + username);
        }
    }
}
