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
    @Size(max = 50, message = "Title must be between 0 and 50 characters")
    public String photoTitle;
    @Size(max=50, message="This field cannot contain more than 50 characters")
    public String camera;
    @Size(max=50, message="This field cannot contain more than 50 characters")
    public String filmStock;
    public String filmFormat;
    public int shotAtIso;
    public String aperture;
    public String shutterSpeed;
    public String exposureCompensation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate rollStarted;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate rollFinished;
    @Size(max=50, message="This field cannot contain more than 50 characters.")
    public String developedByLab;
    public boolean scanned;
    @Size(max=250, message="This field cannot contain more than 250 characters.")
    public String notes;

    public FilmStockInventory filmStockInventory;
    public User user;
}

