package com.example.mmanalog.repositories;

import com.example.mmanalog.models.ProjectFolder;
import com.example.mmanalog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectFolderRepository extends JpaRepository<ProjectFolder, Long> {

    List<ProjectFolder> findProjectFolderByUser(User user);
}
