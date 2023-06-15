package com.example.mmanalog.repositories;

import com.example.mmanalog.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findTagByTagName(String tagName);
}