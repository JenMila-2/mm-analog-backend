package com.example.mmanalog.dtos.OutputDtos;

import com.example.mmanalog.models.User;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate filmExpirationDate;

    public User user;
}
