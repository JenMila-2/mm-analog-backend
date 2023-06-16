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
    @Size(max=50, message="This field cannot contain more than 50 characters.")
    public String filmStockName;
    public int remainingRolls;
    @Size(max=50, message="This field cannot contain more than 50 characters.")
    public String brand;
    @Size(max=50, message="This field cannot contain more than 50 characters.")
    public String stock;
    public String format;
    public int iso;
    public String developmentProcess;
    @Size(max=50, message="This field cannot contain more than 50 characters.")
    public String storage;
    public int rollsShot;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate filmExpirationDate;

    public User user;
}
