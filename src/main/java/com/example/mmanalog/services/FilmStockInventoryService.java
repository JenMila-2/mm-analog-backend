package com.example.mmanalog.services;

import com.example.mmanalog.dtos.OutputDtos.FilmStockInventoryDto;
import com.example.mmanalog.dtos.InputDtos.FilmStockInventoryInputDto;
import com.example.mmanalog.exceptions.UserNotFoundException;
import com.example.mmanalog.models.FilmStockInventory;
import com.example.mmanalog.models.User;
import com.example.mmanalog.repositories.UserRepository;
import com.example.mmanalog.repositories.FilmStockInventoryRepository;
import com.example.mmanalog.exceptions.RecordNotFoundException;
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

    public FilmStockInventoryDto createFilmStockInventory(FilmStockInventoryInputDto filmStockInventoryInputDto) {
        FilmStockInventory filmStockInventory = transferToFilmStockInventory(filmStockInventoryInputDto);
        filmStockInventoryRepository.save(filmStockInventory);

        return transferFilmStockInventoryToDto(filmStockInventory);
    }

    public FilmStockInventoryDto updateFilmStockInventory(Long id, FilmStockInventoryInputDto updatedFilmStockInventory) {

        if (filmStockInventoryRepository.findById(id).isPresent()) {
            FilmStockInventory filmStockInventory = filmStockInventoryRepository.findById(id).get();

            FilmStockInventory filmStockInventory1 = transferToFilmStockInventory(updatedFilmStockInventory);
            filmStockInventory1.setId(filmStockInventory.getId());
            filmStockInventoryRepository.save(filmStockInventory1);

            return transferFilmStockInventoryToDto(filmStockInventory1);
        } else {
            throw new RecordNotFoundException("No film stock inventory found with id: " + id);
        }
    }

    public void deleteFilmStockInventory(@RequestBody Long id) {
        filmStockInventoryRepository.deleteById(id);
    }


    //*-----------------------------Methods related to the relationship between entities-----------------------------*//

    public FilmStockInventoryDto assignFilmStockInventoryToUser(Long id, String username) {
        Optional<FilmStockInventory> optionalFilmStockInventory = filmStockInventoryRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(username);

        if (optionalFilmStockInventory.isPresent() && optionalUser.isPresent()) {
            FilmStockInventory filmStockInventory = optionalFilmStockInventory.get();
            User user = optionalUser.get();

            filmStockInventory.setUser(user);
            filmStockInventoryRepository.save(filmStockInventory);

            return transferFilmStockInventoryToDto(filmStockInventory);
        } else {
            throw new RecordNotFoundException("No film stock inventory found with id: " + id + " or no user found with username: " + username);
        }
    }

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
}
