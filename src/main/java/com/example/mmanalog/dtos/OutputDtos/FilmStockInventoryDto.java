package com.example.mmanalog.dtos.OutputDtos;

import com.example.mmanalog.models.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmStockInventoryDto {

    public Long id;
    public String filmStockName;
    public int remainingRolls;
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
