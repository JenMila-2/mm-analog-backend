package com.example.mmanalog.services;

import com.example.mmanalog.dtos.OutputDtos.PhotoLogDto;
import com.example.mmanalog.dtos.InputDtos.PhotoLogInputDto;
import com.example.mmanalog.models.*;
import com.example.mmanalog.repositories.*;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class PhotoLogService {

    private final PhotoLogRepository photoLogRepository;
    private final ProjectFolderRepository projectFolderRepository;
    private final UserRepository userRepository;

    public PhotoLogService(PhotoLogRepository photoLogRepository, ProjectFolderRepository projectFolderRepository, UserRepository userRepository) {
        this.photoLogRepository = photoLogRepository;
        this.projectFolderRepository = projectFolderRepository;
        this.userRepository = userRepository;
    }

    public List<PhotoLogDto> getPhotoLogs() {
        List<PhotoLog> photoLogs = photoLogRepository.findAll();
        List<PhotoLogDto> photoLogList = new ArrayList<>();

        for (PhotoLog photoLog : photoLogs) {
            photoLogList.add(transferToPhotoLogDto(photoLog));
        }
        return photoLogList;
    }

    public PhotoLogDto getPhotoLog(Long id) {
        Optional<PhotoLog> photoLogOptional = photoLogRepository.findById(id);
        if (photoLogOptional.isPresent()) {
            PhotoLog photoLog = photoLogOptional.get();
            return transferToPhotoLogDto(photoLog);
        } else {
            throw new RecordNotFoundException("No photo log found with id: " + id);
        }
    }

    public List<PhotoLogDto> getAllPhotoLogsByUser(User user) {
        List<PhotoLog> photoLogList = photoLogRepository.findPhotoLogsByUser(user);
        List<PhotoLogDto> photoLogDtoList = new ArrayList<>();
        for(PhotoLog photoLog : photoLogList) {
            PhotoLogDto dto = transferToPhotoLogDto(photoLog);
            photoLogDtoList.add(dto);
        }
        return photoLogDtoList;
    }

    public List<PhotoLogDto> getByPhotoLogFilmStock(String filmStock) {
        List<PhotoLog> photoLogs = photoLogRepository.findByFilmStock(filmStock);
        List<PhotoLogDto> filmStockPhotoLogList = new ArrayList<>();

        for (PhotoLog photoLog : photoLogs) {
            filmStockPhotoLogList.add(transferToPhotoLogDto(photoLog));
        }
        return filmStockPhotoLogList;
    }

    public List<PhotoLogDto> getByPhotoLogFilmFormat(String filmFormat) {
        List<PhotoLog> photoLogs = photoLogRepository.findByFilmFormat(filmFormat);
        List<PhotoLogDto> filmFormatPhotoLogList = new ArrayList<>();

        for (PhotoLog photoLog : photoLogs) {
            filmFormatPhotoLogList.add(transferToPhotoLogDto(photoLog));
        }
        return filmFormatPhotoLogList;
    }

    public PhotoLogDto createPhotoLog(PhotoLogInputDto inputDtoPhotoLog) {
        PhotoLog photoLog = transferToPhotoLog(inputDtoPhotoLog);
        photoLogRepository.save(photoLog);

        return transferToPhotoLogDto(photoLog);
    }

    public void updatePhotoLog(Long id, PhotoLogDto updatedPhotoLog) {
        if (!photoLogRepository.existsById(id)) {
            throw new RecordNotFoundException("No photo log found with id: " + id);
        }
        PhotoLog storedPhotoLog = photoLogRepository.findById(id).orElse(null);
        storedPhotoLog.setPhotoTitle(updatedPhotoLog.getPhotoTitle());
        storedPhotoLog.setCamera(updatedPhotoLog.getCamera());
        storedPhotoLog.setFilmStock(updatedPhotoLog.getFilmStock());
        storedPhotoLog.setFilmFormat(updatedPhotoLog.getFilmFormat());
        storedPhotoLog.setShotAtIso(updatedPhotoLog.getShotAtIso());
        storedPhotoLog.setAperture(updatedPhotoLog.getAperture());
        storedPhotoLog.setShutterSpeed(updatedPhotoLog.getShutterSpeed());
        storedPhotoLog.setExposureCompensation(updatedPhotoLog.getExposureCompensation());
        storedPhotoLog.setDateTaken(updatedPhotoLog.getDateTaken());
        storedPhotoLog.setDevelopedByLab(updatedPhotoLog.getDevelopedByLab());
        storedPhotoLog.setNotes(updatedPhotoLog.getNotes());
        storedPhotoLog.setProjectFolder(updatedPhotoLog.getProjectFolder());
        storedPhotoLog.setUser(updatedPhotoLog.getUser());

            photoLogRepository.save(storedPhotoLog);

        //transferToPhotoLogDto(photoLogRepository.save(storedPhotoLog));
    }

    public void deletePhotoLog(@RequestBody Long id) {
        photoLogRepository.deleteById(id);
    }


    //*-----------------------------Methods related to the relationship between entities-----------------------------*//

    public PhotoLogDto createPhotoLogForUser(String username, PhotoLogInputDto photoLogInputDto) {
        Optional<User> userOptional = userRepository.findById(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            PhotoLog photoLog = new PhotoLog();
            photoLog.setUser(user);
            photoLog.setPhotoTitle(photoLogInputDto.getPhotoTitle());
            photoLog.setCamera(photoLogInputDto.getCamera());
            photoLog.setFilmStock(photoLogInputDto.getFilmStock());
            photoLog.setFilmFormat(photoLogInputDto.getFilmFormat());
            photoLog.setShotAtIso(photoLogInputDto.getShotAtIso());
            photoLog.setAperture(photoLogInputDto.getAperture());
            photoLog.setShutterSpeed(photoLogInputDto.getShutterSpeed());
            photoLog.setExposureCompensation(photoLogInputDto.getExposureCompensation());
            photoLog.setDateTaken(photoLogInputDto.getDateTaken());
            photoLog.setDevelopedByLab(photoLogInputDto.getDevelopedByLab());
            photoLog.setNotes(photoLogInputDto.getNotes());

            photoLogRepository.save(photoLog);

            return transferToPhotoLogDto(photoLog);
        } else {
            throw new UserNotFoundException("No user found with username: " + username);
        }
    }

    public PhotoLogDto createPhotoLogForProjectFolderForUser(String username, Long folderId, PhotoLogInputDto photoLogInput) {
        Optional<User> userOptional = userRepository.findById(username);
        Optional<ProjectFolder> projectFolderOptional = projectFolderRepository.findById(folderId);

        if (userOptional.isPresent() && projectFolderOptional.isPresent()) {
            User user = userOptional.get();
            ProjectFolder projectFolder = projectFolderOptional.get();

            PhotoLog photoLog = new PhotoLog();
            photoLog.setUser(user);
            photoLog.setProjectFolder(projectFolder);
            photoLog.setPhotoTitle(photoLogInput.getPhotoTitle());
            photoLog.setCamera(photoLogInput.getCamera());
            photoLog.setFilmStock(photoLogInput.getFilmStock());
            photoLog.setFilmFormat(photoLogInput.getFilmFormat());
            photoLog.setShotAtIso(photoLogInput.getShotAtIso());
            photoLog.setAperture(photoLogInput.getAperture());
            photoLog.setShutterSpeed(photoLogInput.getShutterSpeed());
            photoLog.setExposureCompensation(photoLogInput.getExposureCompensation());
            photoLog.setDateTaken(photoLogInput.getDateTaken());
            photoLog.setDevelopedByLab(photoLogInput.getDevelopedByLab());
            photoLog.setNotes(photoLogInput.getNotes());

            photoLogRepository.save(photoLog);

            return transferToPhotoLogDto(photoLog);
        } else {
            throw new RecordNotFoundException("No user with username: " + username + " or project folder with id: " + folderId + " found");
        }
    }

    //*---------------------------------Transfers---------------------------------*//

    public PhotoLog transferToPhotoLog(PhotoLogInputDto photoLogInputDto) {
        PhotoLog photoLog = new PhotoLog();

        photoLog.setId(photoLogInputDto.getId());
        photoLog.setPhotoTitle(photoLogInputDto.getPhotoTitle());
        photoLog.setCamera(photoLogInputDto.getCamera());
        photoLog.setFilmStock(photoLogInputDto.getFilmStock());
        photoLog.setFilmFormat(photoLogInputDto.getFilmFormat());
        photoLog.setShotAtIso(photoLogInputDto.getShotAtIso());
        photoLog.setAperture(photoLogInputDto.getAperture());
        photoLog.setShutterSpeed(photoLogInputDto.getShutterSpeed());
        photoLog.setExposureCompensation(photoLogInputDto.getExposureCompensation());
        photoLog.setDateTaken(photoLogInputDto.getDateTaken());
        photoLog.setDevelopedByLab(photoLogInputDto.getDevelopedByLab());
        photoLog.setNotes(photoLogInputDto.getNotes());

        return photoLog;
    }

    public PhotoLogDto transferToPhotoLogDto(PhotoLog photoLog) {
        PhotoLogDto photoLogDto = new PhotoLogDto();

        photoLogDto.setId(photoLog.getId());
        photoLogDto.setPhotoTitle(photoLog.getPhotoTitle());
        photoLogDto.setCamera(photoLog.getCamera());
        photoLogDto.setFilmStock(photoLog.getFilmStock());
        photoLogDto.setFilmFormat(photoLog.getFilmFormat());
        photoLogDto.setShotAtIso(photoLog.getShotAtIso());
        photoLogDto.setAperture(photoLog.getAperture());
        photoLogDto.setShutterSpeed(photoLog.getShutterSpeed());
        photoLogDto.setExposureCompensation(photoLog.getExposureCompensation());
        photoLogDto.setDateTaken(photoLog.getDateTaken());
        photoLogDto.setDevelopedByLab(photoLog.getDevelopedByLab());
        photoLogDto.setNotes(photoLog.getNotes());
        photoLogDto.setProjectFolder(photoLog.getProjectFolder());
        photoLogDto.setUser(photoLog.getUser());

        return photoLogDto;
    }
}



