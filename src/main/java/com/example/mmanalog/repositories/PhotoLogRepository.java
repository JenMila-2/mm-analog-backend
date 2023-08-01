package com.example.mmanalog.repositories;

import com.example.mmanalog.models.PhotoLog;
import com.example.mmanalog.models.ProjectFolder;
import com.example.mmanalog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoLogRepository extends JpaRepository<PhotoLog, Long> {
    List<PhotoLog> findByStock(String filmStock);

    List<PhotoLog> findPhotoLogsByUser(User user);

    List<PhotoLog> findPhotoLogByProjectFolder(ProjectFolder projectFolder);
}