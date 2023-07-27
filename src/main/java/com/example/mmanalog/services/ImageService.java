/*package com.example.mmanalog.services;

import com.example.mmanalog.models.Image;
import com.example.mmanalog.repositories.ImageRepository;
import com.example.mmanalog.utilities.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image uploadImage(MultipartFile file) throws IOException {
        Image newImage = new Image();
        newImage.setName(file.getOriginalFilename());
        newImage.setType(file.getContentType());
        newImage.setImageData(ImageUtility.compressImage(file.getBytes()));
        return imageRepository.save(newImage);
    }

    public byte[] downloadImage(String fileName){
        Optional<Image> imageData = imageRepository.findByName(fileName);
        return ImageUtility.decompressImage(imageData.get().getImageData());
    }
}*/
