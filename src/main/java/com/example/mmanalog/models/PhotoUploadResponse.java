package com.example.mmanalog.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PhotoUploadResponse {

    @Id
    @GeneratedValue
    private String fileName;
    private String contentType;
    private String imageUrl;
}