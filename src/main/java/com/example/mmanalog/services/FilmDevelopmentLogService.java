package com.example.mmanalog.services;

import com.example.mmanalog.dtos.OutputDtos.FilmDevelopmentLogDto;
import com.example.mmanalog.dtos.InputDtos.FilmDevelopmentLogInputDto;
import com.example.mmanalog.exceptions.UserNotFoundException;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.models.FilmDevelopmentLog;
import com.example.mmanalog.models.User;
import com.example.mmanalog.repositories.*;
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

    public FilmDevelopmentLogDto createFilmDevelopmentLog(FilmDevelopmentLogInputDto filmDevelopmentLogInputDto) {
        FilmDevelopmentLog filmDevelopmentLog = transferToFilmDevelopmentLog(filmDevelopmentLogInputDto);
        filmDevelopmentLogRepository.save(filmDevelopmentLog);

        return transferFilmDevelopmentLogToDto(filmDevelopmentLog);
    }

    public FilmDevelopmentLogDto updateFilmDevelopmentLog(Long id, FilmDevelopmentLogInputDto updatedFilmDevelopmentLog) {

        if (filmDevelopmentLogRepository.findById(id).isPresent()) {
            FilmDevelopmentLog filmDevelopmentLog = filmDevelopmentLogRepository.findById(id).get();

            FilmDevelopmentLog filmDevelopmentLog1 = transferToFilmDevelopmentLog(updatedFilmDevelopmentLog);
            filmDevelopmentLog1.setId(filmDevelopmentLog.getId());
            filmDevelopmentLogRepository.save(filmDevelopmentLog1);

            return transferFilmDevelopmentLogToDto(filmDevelopmentLog1);
        } else {
            throw new RecordNotFoundException("No film development log found with id: " + id);
        }
    }

    public void deleteFilmDevelopmentLog(@RequestBody Long id) {
        filmDevelopmentLogRepository.deleteById(id);
    }


    //*-----------------------------Methods related to the relationship between entities-----------------------------*//

    public FilmDevelopmentLogDto assignFilmDevelopmentLogToUser(Long id, String username) {
        Optional<FilmDevelopmentLog> optionalFilmDevelopmentLog = filmDevelopmentLogRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(username);

        if (optionalFilmDevelopmentLog.isPresent() && optionalUser.isPresent()) {
            FilmDevelopmentLog filmDevelopmentLog = optionalFilmDevelopmentLog.get();
            User user = optionalUser.get();

            filmDevelopmentLog.setUser(user);
            filmDevelopmentLogRepository.save(filmDevelopmentLog);

            return transferFilmDevelopmentLogToDto(filmDevelopmentLog);
        } else {
            throw new RecordNotFoundException("No film development log with id: " + id + " or user found with username: " + username);
        }
    }

    public FilmDevelopmentLogDto createFilmDevelopmentLogForUser(String username, FilmDevelopmentLogInputDto filmDevelopmentLogInputDto) {
        Optional<User> userOptional = userRepository.findById(username);

        if(userOptional.isPresent()) {
            User user = userOptional.get();

            FilmDevelopmentLog filmDevelopmentLog = new FilmDevelopmentLog();
            filmDevelopmentLog.setUser(user);
            filmDevelopmentLog.setNameFilm(filmDevelopmentLogInputDto.getNameFilm());
            filmDevelopmentLog.setProject(filmDevelopmentLogInputDto.getProject());
            filmDevelopmentLog.setCamera(filmDevelopmentLogInputDto.getCamera());
            filmDevelopmentLog.setFilmStock(filmDevelopmentLogInputDto.getFilmStock());
            filmDevelopmentLog.setFilmFormat(filmDevelopmentLogInputDto.getFilmFormat());
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

    public FilmDevelopmentLog transferToFilmDevelopmentLog(FilmDevelopmentLogInputDto filmDevelopmentLogInputDto) {
        FilmDevelopmentLog filmDevelopmentLog = new FilmDevelopmentLog();

        filmDevelopmentLog.setId(filmDevelopmentLogInputDto.getId());
        filmDevelopmentLog.setNameFilm(filmDevelopmentLogInputDto.getNameFilm());
        filmDevelopmentLog.setProject(filmDevelopmentLogInputDto.getProject());
        filmDevelopmentLog.setCamera(filmDevelopmentLogInputDto.getCamera());
        filmDevelopmentLog.setFilmStock(filmDevelopmentLogInputDto.getFilmStock());
        filmDevelopmentLog.setFilmFormat(filmDevelopmentLogInputDto.getFilmFormat());
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
        filmDevelopmentLogDto.setNameFilm(filmDevelopmentLog.getNameFilm());
        filmDevelopmentLogDto.setProject(filmDevelopmentLog.getProject());
        filmDevelopmentLogDto.setCamera(filmDevelopmentLog.getCamera());
        filmDevelopmentLogDto.setFilmStock(filmDevelopmentLog.getFilmStock());
        filmDevelopmentLogDto.setFilmFormat(filmDevelopmentLog.getFilmFormat());
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
}
