package com.example.mmanalog.repositories;

import com.example.mmanalog.models.FileUploadResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<FileUploadResponse, String> {

    Optional<FileUploadResponse> findByFileName(String fileName);

    List<FileUploadResponse> findByProjectFolderId(Long folderId);
}
