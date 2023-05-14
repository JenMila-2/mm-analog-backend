package com.example.mmanalog.repositories;

import com.example.mmanalog.models.Photo;
import com.example.mmanalog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findByUser(User user);

}
