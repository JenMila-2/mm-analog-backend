package com.example.mmanalog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "filmstockinventories")
public class FilmStockInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filmStockName;
    private int remainingRolls;
    private String brand;
    private String stock;
    private String format;
    private int iso;
    private String developmentProcess;
    private String storage;
    private int rollsShot;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate filmExpirationDate;

    @ManyToOne
    @JoinColumn(name = "username")
    @JsonIgnore
    private User user;
}

