package com.example.mmanalog.dtos.InputDtos;

import com.example.mmanalog.models.User;
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
public class FilmStockInventoryInputDto {

    public Long id;
    @Size(min = 3, max = 75, message = "This field must be between 3 and 75 characters long")
    public String filmStockName;
    public int remainingRolls;
    @Size(min = 3, max = 75, message = "This field must be between 3 and 75 characters long")
    public String brand;
    @Size(min = 3, max = 75, message = "This field must be between 3 and 75 characters long")
    public String stock;
    public String format;
    public int iso;
    public String developmentProcess;
    @Size(min = 3, max = 75, message = "This field must be between 3 and 75 characters long")
    public String storage;
    public int rollsShot;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate filmExpirationDate;

    public User user;
}
