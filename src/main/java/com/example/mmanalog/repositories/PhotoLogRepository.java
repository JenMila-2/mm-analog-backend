package com.example.mmanalog.repositories;

import com.example.mmanalog.models.PhotoLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoLogRepository extends JpaRepository<PhotoLog, Long> {
    List<PhotoLog> findByFilmStock(String filmStock);
}

