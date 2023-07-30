package com.example.mmanalog.services;

import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.models.FileUploadResponse;
import com.example.mmanalog.models.ProjectFolder;
import com.example.mmanalog.models.User;
import com.example.mmanalog.repositories.FileUploadRepository;
import com.example.mmanalog.repositories.UserRepository;
import com.example.mmanalog.repositories.ProjectFolderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FileService {

    private final Path fileStoragePath;
    private final String fileStorageLocation;
    private final FileUploadRepository repository;
    private final UserRepository userRepository;
    private final ProjectFolderRepository projectFolderRepository;

    public FileService(@Value("${my.upload_location}") String fileStorageLocation, FileUploadRepository repository, UserRepository userRepository, ProjectFolderRepository projectFolderRepository) {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        this.fileStorageLocation = fileStorageLocation;
        this.repository = repository;
        this.userRepository = userRepository;
        this.projectFolderRepository = projectFolderRepository;

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory");
        }
    }

    public String uploadFile(MultipartFile file, String url) {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Path filePath = Paths.get(fileStoragePath + "/" + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Issue in storing the selected file", e);
        }

        repository.save(new FileUploadResponse(fileName, file.getContentType(), url));

        return fileName;
    }

    public Resource downloadFile(String fileName) {

        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);

        Resource resource;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading the file", e);
        }

        if(resource.exists()&& resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("The file doesn't exist or is not readable");
        }
    }

    ///
    public String uploadFileForUser(MultipartFile file, String username) {
        User user = userRepository.findById(username).orElseThrow(() -> new RuntimeException("User not found."));

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(fileStoragePath + "/" + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Issue in storing the selected file", e);
        }

        FileUploadResponse uploadedFile = new FileUploadResponse(fileName, file.getContentType(), null);
        uploadedFile.setUser(user);
        repository.save(uploadedFile);

        return fileName;
    }

    public Resource downloadFileForUser(String username, String fileName) {
        User user = userRepository.findById(username).orElseThrow(() -> new RuntimeException("User not found."));

        FileUploadResponse file = repository.findByFileName(fileName).orElseThrow(() -> new RecordNotFoundException("File not found for the given user or file has been deleted."));

        if (!file.getUser().getUsername().equals(username)) {
            throw new RecordNotFoundException("No file found for user: " + username);
        }

        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);

        Resource resource;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading the file", e);
        }

        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("The file doesn't exist or is not readable");
        }
    }

    public void deleteFileForUser(String username, String fileName) {
        User user = userRepository.findById(username).orElseThrow(() -> new RuntimeException("User not found."));

        FileUploadResponse file = repository.findByFileName(fileName).orElseThrow(() -> new RuntimeException("File not found."));
        if (!file.getUser().getUsername().equals(username)) {
            throw new RuntimeException("File not found for the given user.");
        }

        Path filePath = Paths.get(fileStoragePath + "/" + fileName);
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete the file", e);
        }

        repository.delete(file);
    }
}
