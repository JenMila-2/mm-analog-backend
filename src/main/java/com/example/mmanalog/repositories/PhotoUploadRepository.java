package com.example.mmanalog.repositories;

import com.example.mmanalog.models.PhotoUploadResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoUploadRepository extends JpaRepository<PhotoUploadResponse,String> {

    Optional<PhotoUploadResponse> findByFileName(String fileName);

}
