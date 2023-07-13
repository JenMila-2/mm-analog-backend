package com.example.mmanalog.repositories;

import com.example.mmanalog.models.FilmDevelopmentLog;
import com.example.mmanalog.models.PhotoLog;
import com.example.mmanalog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoLogRepository extends JpaRepository<PhotoLog, Long> {
    List<PhotoLog> findByFilmStock(String filmStock);

    List<PhotoLog> findByFilmFormat(String filmFormat);

    List<PhotoLog> findPhotoLogsByUser(User user);
}