package com.example.mmanalog.repositories;

import com.example.mmanalog.models.FilmDevelopmentLog;
import com.example.mmanalog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmDevelopmentLogRepository extends JpaRepository<FilmDevelopmentLog, Long> {

    List<FilmDevelopmentLog> findFilmDevelopmentLogsByUser(User user);
}
