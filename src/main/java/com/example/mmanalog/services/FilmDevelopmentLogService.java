package com.example.mmanalog.services;

import com.example.mmanalog.dtos.OutputDtos.FilmDevelopmentLogDto;
import com.example.mmanalog.dtos.InputDtos.FilmDevelopmentLogInputDto;
import com.example.mmanalog.models.FilmDevelopmentLog;
import com.example.mmanalog.models.User;
import com.example.mmanalog.repositories.*;
import com.example.mmanalog.exceptions.UserNotFoundException;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class FilmDevelopmentLogService {

    private final FilmDevelopmentLogRepository filmDevelopmentLogRepository;
    private final UserRepository userRepository;

    public FilmDevelopmentLogService(FilmDevelopmentLogRepository filmDevelopmentLogRepository, UserRepository userRepository) {
        this.filmDevelopmentLogRepository = filmDevelopmentLogRepository;
        this.userRepository = userRepository;
    }

    public List<FilmDevelopmentLogDto> getFilmDevelopmentLogs() {
        List<FilmDevelopmentLog> filmDevelopmentLogs = filmDevelopmentLogRepository.findAll();
        List<FilmDevelopmentLogDto> filmDevelopmentLogList = new ArrayList<>();

        for (FilmDevelopmentLog filmDevelopmentLog : filmDevelopmentLogs) {
            filmDevelopmentLogList.add(transferFilmDevelopmentLogToDto(filmDevelopmentLog));
        }
        return filmDevelopmentLogList;
    }

    public FilmDevelopmentLogDto getFilmDevelopmentLog(Long id) {
        Optional<FilmDevelopmentLog> filmDevelopmentLogOptional = filmDevelopmentLogRepository.findById(id);
        if (filmDevelopmentLogOptional.isPresent()) {
            FilmDevelopmentLog filmDevelopmentLog = filmDevelopmentLogOptional.get();
            return transferFilmDevelopmentLogToDto((filmDevelopmentLog));
        } else {
            throw new RecordNotFoundException("No film development log found with id: " + id);
        }
    }

    public List<FilmDevelopmentLogDto> getAllFilmDevelopmentLogsByUser(User user) {
        List<FilmDevelopmentLog> filmDevelopmentLogList = filmDevelopmentLogRepository.findFilmDevelopmentLogsByUser(user);
        List<FilmDevelopmentLogDto> filmDevelopmentLogDtoList = new ArrayList<>();
        for(FilmDevelopmentLog filmDevelopmentLog : filmDevelopmentLogList) {
            FilmDevelopmentLogDto dto = transferFilmDevelopmentLogToDto(filmDevelopmentLog);
            filmDevelopmentLogDtoList.add(dto);
        }
        return filmDevelopmentLogDtoList;
    }

    public FilmDevelopmentLogDto createFilmDevelopmentLog(FilmDevelopmentLogInputDto filmDevelopmentLogInputDto) {
        FilmDevelopmentLog filmDevelopmentLog = transferToFilmDevelopmentLog(filmDevelopmentLogInputDto);
        filmDevelopmentLogRepository.save(filmDevelopmentLog);

        return transferFilmDevelopmentLogToDto(filmDevelopmentLog);
    }

    public void updateFilmDevelopmentLog(Long id, FilmDevelopmentLogDto updatedFilmDevelopmentLog) {

        if(!filmDevelopmentLogRepository.existsById(id)) {
            throw new RecordNotFoundException("No film development log found with id: " + id);
        }
        FilmDevelopmentLog storedFilmDevelopmentLog = filmDevelopmentLogRepository.findById(id).orElse(null);
        storedFilmDevelopmentLog.setRollName(updatedFilmDevelopmentLog.getRollName());
        storedFilmDevelopmentLog.setProject(updatedFilmDevelopmentLog.getProject());
        storedFilmDevelopmentLog.setCamera(updatedFilmDevelopmentLog.getCamera());
        storedFilmDevelopmentLog.setStock(updatedFilmDevelopmentLog.getStock());
        storedFilmDevelopmentLog.setFormat(updatedFilmDevelopmentLog.getFormat());
        storedFilmDevelopmentLog.setShotAtIso(updatedFilmDevelopmentLog.getShotAtIso());
        storedFilmDevelopmentLog.setDevelopmentProcess(updatedFilmDevelopmentLog.getDevelopmentProcess());
        storedFilmDevelopmentLog.setStatus(updatedFilmDevelopmentLog.getStatus());
        storedFilmDevelopmentLog.setRollStarted(updatedFilmDevelopmentLog.getRollStarted());
        storedFilmDevelopmentLog.setRollFinished(updatedFilmDevelopmentLog.getRollFinished());
        storedFilmDevelopmentLog.setExposed(updatedFilmDevelopmentLog.isExposed());
        storedFilmDevelopmentLog.setDeveloped(updatedFilmDevelopmentLog.isDeveloped());
        storedFilmDevelopmentLog.setScanned(updatedFilmDevelopmentLog.isScanned());
        storedFilmDevelopmentLog.setDevelopedByLab(updatedFilmDevelopmentLog.getDevelopedByLab());
        storedFilmDevelopmentLog.setUser(updatedFilmDevelopmentLog.getUser());

        filmDevelopmentLogRepository.save(storedFilmDevelopmentLog);
    }

    public void deleteFilmDevelopmentLog(@RequestBody Long id) {
        filmDevelopmentLogRepository.deleteById(id);
    }

    //*---------------------------------Transfers---------------------------------*//

    public FilmDevelopmentLog transferToFilmDevelopmentLog(FilmDevelopmentLogInputDto filmDevelopmentLogInputDto) {
        FilmDevelopmentLog filmDevelopmentLog = new FilmDevelopmentLog();

        filmDevelopmentLog.setId(filmDevelopmentLogInputDto.getId());
        filmDevelopmentLog.setRollName(filmDevelopmentLogInputDto.getRollName());
        filmDevelopmentLog.setProject(filmDevelopmentLogInputDto.getProject());
        filmDevelopmentLog.setCamera(filmDevelopmentLogInputDto.getCamera());
        filmDevelopmentLog.setStock(filmDevelopmentLogInputDto.getStock());
        filmDevelopmentLog.setFormat(filmDevelopmentLogInputDto.getFormat());
        filmDevelopmentLog.setShotAtIso(filmDevelopmentLogInputDto.getShotAtIso());
        filmDevelopmentLog.setDevelopmentProcess(filmDevelopmentLogInputDto.getDevelopmentProcess());
        filmDevelopmentLog.setStatus(filmDevelopmentLogInputDto.getStatus());
        filmDevelopmentLog.setRollStarted(filmDevelopmentLogInputDto.getRollStarted());
        filmDevelopmentLog.setRollFinished(filmDevelopmentLogInputDto.getRollFinished());
        filmDevelopmentLog.setExposed(filmDevelopmentLogInputDto.isExposed());
        filmDevelopmentLog.setDeveloped(filmDevelopmentLogInputDto.isDeveloped());
        filmDevelopmentLog.setScanned(filmDevelopmentLogInputDto.isScanned());
        filmDevelopmentLog.setDevelopedByLab(filmDevelopmentLogInputDto.getDevelopedByLab());

        return filmDevelopmentLog;
    }

    public FilmDevelopmentLogDto transferFilmDevelopmentLogToDto(FilmDevelopmentLog filmDevelopmentLog) {
        FilmDevelopmentLogDto filmDevelopmentLogDto = new FilmDevelopmentLogDto();

        filmDevelopmentLogDto.setId(filmDevelopmentLog.getId());
        filmDevelopmentLogDto.setRollName(filmDevelopmentLog.getRollName());
        filmDevelopmentLogDto.setProject(filmDevelopmentLog.getProject());
        filmDevelopmentLogDto.setCamera(filmDevelopmentLog.getCamera());
        filmDevelopmentLogDto.setStock(filmDevelopmentLog.getStock());
        filmDevelopmentLogDto.setFormat(filmDevelopmentLog.getFormat());
        filmDevelopmentLogDto.setShotAtIso(filmDevelopmentLog.getShotAtIso());
        filmDevelopmentLogDto.setDevelopmentProcess(filmDevelopmentLog.getDevelopmentProcess());
        filmDevelopmentLogDto.setStatus(filmDevelopmentLog.getStatus());
        filmDevelopmentLogDto.setRollStarted(filmDevelopmentLog.getRollStarted());
        filmDevelopmentLogDto.setRollFinished(filmDevelopmentLog.getRollFinished());
        filmDevelopmentLogDto.setExposed(filmDevelopmentLog.isExposed());
        filmDevelopmentLogDto.setDeveloped(filmDevelopmentLog.isDeveloped());
        filmDevelopmentLogDto.setScanned(filmDevelopmentLog.isScanned());
        filmDevelopmentLogDto.setDevelopedByLab(filmDevelopmentLog.getDevelopedByLab());
        filmDevelopmentLogDto.setUser(filmDevelopmentLog.getUser());

        return filmDevelopmentLogDto;
    }

    //*-----------------------------Methods related to the relationship between entities-----------------------------*//

    public FilmDevelopmentLogDto createFilmDevelopmentLogForUser(String username, FilmDevelopmentLogInputDto filmDevelopmentLogInputDto) {
        Optional<User> userOptional = userRepository.findById(username);

        if(userOptional.isPresent()) {
            User user = userOptional.get();

            FilmDevelopmentLog filmDevelopmentLog = new FilmDevelopmentLog();
            filmDevelopmentLog.setUser(user);
            filmDevelopmentLog.setRollName(filmDevelopmentLogInputDto.getRollName());
            filmDevelopmentLog.setProject(filmDevelopmentLogInputDto.getProject());
            filmDevelopmentLog.setCamera(filmDevelopmentLogInputDto.getCamera());
            filmDevelopmentLog.setStock(filmDevelopmentLogInputDto.getStock());
            filmDevelopmentLog.setFormat(filmDevelopmentLogInputDto.getFormat());
            filmDevelopmentLog.setShotAtIso(filmDevelopmentLogInputDto.getShotAtIso());
            filmDevelopmentLog.setDevelopmentProcess(filmDevelopmentLogInputDto.getDevelopmentProcess());
            filmDevelopmentLog.setStatus(filmDevelopmentLogInputDto.getStatus());
            filmDevelopmentLog.setRollStarted(filmDevelopmentLogInputDto.getRollStarted());
            filmDevelopmentLog.setRollFinished(filmDevelopmentLogInputDto.getRollFinished());
            filmDevelopmentLog.setExposed(filmDevelopmentLogInputDto.isExposed());
            filmDevelopmentLog.setDeveloped(filmDevelopmentLogInputDto.isDeveloped());
            filmDevelopmentLog.setScanned(filmDevelopmentLogInputDto.isScanned());
            filmDevelopmentLog.setDevelopedByLab(filmDevelopmentLogInputDto.getDevelopedByLab());

            filmDevelopmentLogRepository.save(filmDevelopmentLog);

            return transferFilmDevelopmentLogToDto(filmDevelopmentLog);
        } else {
            throw new UserNotFoundException("No user found with username: " + username);
        }
    }
}
