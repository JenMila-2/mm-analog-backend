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
    @Size(min = 3, max = 75, message = "This field must be between 3 and 75 characters long")
    public String title;
    @Size(min = 3, max = 75, message = "This field must be between 3 and 75 characters long")
    public String camera;
    @Size(min = 3, max = 75, message = "This field must be between 3 and 75 characters long")
    public String stock;
    public int iso;
    @Size(min = 3, max = 75, message = "This field must be between 3 and 75 characters long")
    public String aperture;
    @Size(min = 3, max = 75, message = "This field must be between 3 and 75 characters long")
    public String shutterSpeed;
    @Size(max = 5, message = "This field cannot contain more than 5 characters")
    public String exposureCompensation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate dateTaken;
    @Size(min = 10, max = 250, message = "This field must be between 10 and 250 characters long")
    public String notes;

    public User user;
}