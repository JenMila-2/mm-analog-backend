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
import java.util.Objects;

@Service
public class FileService {

    private final Path fileStoragePath;
    private final String fileStorageLocation;
    private final FileUploadRepository fileRepository;
    private final UserRepository userRepository;
    private final ProjectFolderRepository projectFolderRepository;

    public FileService(@Value("${my.upload_location}") String fileStorageLocation, FileUploadRepository fileRepository, UserRepository userRepository, ProjectFolderRepository projectFolderRepository) {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        this.fileStorageLocation = fileStorageLocation;
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
        this.projectFolderRepository = projectFolderRepository;

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory");
        }
    }

    // *** Important side note: project mm-analog was created using an Apple (Mac) computer. If you are using a Windows computer the Path filePath in the upload methods should be changed to: Path filePath = Paths.get(fileStoragePath + "\\" + fileName); Do not forget to also change the Path in the delete methods! *** //

    public String uploadFile(MultipartFile file, String url) {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Path filePath = Paths.get(fileStoragePath + "/" + fileName);
        //For Windows users: Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Issue in storing the selected file", e);
        }

        fileRepository.save(new FileUploadResponse(fileName, file.getContentType(), url));

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

    //*-----------------------------Methods related to the relationship between entities-----------------------------*//

    public String uploadFileForUser(MultipartFile file, String username, String url) {
        User user = userRepository.findById(username).orElseThrow(() -> new RuntimeException("User not found"));

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(fileStoragePath + "/" + fileName);
        //For Windows users: Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Issue in storing the selected file", e);
        }

        FileUploadResponse uploadedFile = new FileUploadResponse(fileName, file.getContentType(), url);
        uploadedFile.setUser(user);
        fileRepository.save(uploadedFile);

        return fileName;
    }

    public Resource downloadFileForUser(String username, String fileName) {
        User user = userRepository.findById(username).orElseThrow(() -> new RuntimeException("User not found"));

        FileUploadResponse file = fileRepository.findByFileName(fileName).orElseThrow(() -> new RecordNotFoundException("File not found for the user or file has already been deleted"));

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
        User user = userRepository.findById(username).orElseThrow(() -> new RuntimeException("User not found"));

        FileUploadResponse file = fileRepository.findByFileName(fileName).orElseThrow(() -> new RuntimeException("File not found"));
        if (!file.getUser().getUsername().equals(username)) {
            throw new RuntimeException("File not found for the user");
        }

        Path filePath = Paths.get(fileStoragePath + "/" + fileName);
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete the file", e);
        }

        fileRepository.delete(file);
    }

    public String assignFileToFolder(MultipartFile file, Long folderId, String url) {
        ProjectFolder projectFolder = projectFolderRepository.findById(folderId)
                .orElseThrow(() -> new RecordNotFoundException("Project folder not found"));

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(fileStoragePath + "/" + fileName);
        //For Windows users: Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Issue in storing the selected file", e);
        }

        FileUploadResponse uploadedFile = new FileUploadResponse(fileName, file.getContentType(), url);
        uploadedFile.setProjectFolder(projectFolder);
        fileRepository.save(uploadedFile);

        return fileName;
    }

    public Resource downloadFileFromFolder(Long folderId, String fileName) {
        ProjectFolder projectFolder = projectFolderRepository.findById(folderId).orElseThrow(() -> new RuntimeException("Project folder not found"));

        FileUploadResponse file = fileRepository.findByFileName(fileName).orElseThrow(() -> new RecordNotFoundException("File not found in the project folder or the file has already been deleted"));

        if (!file.getProjectFolder().getId().equals(folderId)) {
            throw new RecordNotFoundException("No file found for project folder: " + folderId);
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

    public void deleteFileFromFolder(Long folderId, String fileName) {
        ProjectFolder projectFolder = projectFolderRepository.findById(folderId).orElseThrow(() -> new RuntimeException("Project folder not found"));

        FileUploadResponse file = fileRepository.findByFileName(fileName).orElseThrow(() -> new RuntimeException("File not found"));
        if (!file.getProjectFolder().getId().equals(folderId)) {
            throw new RuntimeException("File not found in project folder" + folderId);
        }

        Path filePath = Paths.get(fileStoragePath + "/" + fileName);
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete the file", e);
        }

        fileRepository.delete(file);
    }

    public String assignFileToFolderByUser(MultipartFile file, Long folderId, String url, String username) {
        ProjectFolder projectFolder = projectFolderRepository.findById(folderId)
                .orElseThrow(() -> new RecordNotFoundException("Project folder not found"));

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(fileStoragePath + "/" + fileName);
        // For Windows users: Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Issue in storing the selected file", e);
        }

        FileUploadResponse uploadedFile = new FileUploadResponse(fileName, file.getContentType(), url);

        User user = userRepository.findById(username)
                .orElseThrow(() -> new RecordNotFoundException("User not found"));
        uploadedFile.setUser(user);

        uploadedFile.setProjectFolder(projectFolder);
        fileRepository.save(uploadedFile);

        return fileName;
    }

    public Resource downloadFileFromFolderByUser(Long folderId, String fileName, String username) {
        ProjectFolder projectFolder = projectFolderRepository.findById(folderId)
                .orElseThrow(() -> new RuntimeException("Project folder not found"));

        FileUploadResponse file = fileRepository.findByFileName(fileName)
                .orElseThrow(() -> new RecordNotFoundException("File not found in the project folder or the file has already been deleted"));

        if (!file.getProjectFolder().getId().equals(folderId)) {
            throw new RecordNotFoundException("No file found for project folder: " + folderId);
        }

        if (!file.getUser().getUsername().equals(username)) {
            throw new RecordNotFoundException("The file does not belong to the user: " + username);
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
}
