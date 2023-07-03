package com.example.mmanalog.dtos.InputDtos;

import com.example.mmanalog.models.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoLogInputDto {

    public Long id;
    @NotNull
    @Size(max=75, message="Title must be between 5 and 75 characters")
    public String photoTitle;
    @Size(max=75, message="This field cannot contain more than 75 characters")
    public String camera;
    @Size(max=75, message="This field cannot contain more than 75 characters")
    public String filmStock;
    @Size(max=75, message="This field cannot contain more than 75 characters")
    public String filmFormat;
    public int shotAtIso;
    @Size(max=75, message="This field cannot contain more than 75 characters")
    public String aperture;
    @Size(max=75, message="This field cannot contain more than 75 characters")
    public String shutterSpeed;
    @Size(max=75, message="This field cannot contain more than 75 characters")
    public String exposureCompensation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate dateTaken;
    @Size(max=75, message="This field cannot contain more than 75 characters")
    public String developedByLab;
    @Size(max=250, message="This field cannot contain more than 250 characters")
    public String notes;

    public User user;
}