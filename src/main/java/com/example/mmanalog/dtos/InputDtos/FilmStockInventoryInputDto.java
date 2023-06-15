package com.example.mmanalog.dtos.InputDtos;

import com.example.mmanalog.models.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
    public String stock;
    public String format;
    public int iso;
    public String developmentProcess;
    public String storage;
    public int rollsShot;
    @JsonFormat(pattern="yyyy-MM-dd")
    public LocalDate filmExpirationDate;

    public User user;
}
