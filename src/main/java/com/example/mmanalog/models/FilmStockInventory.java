package com.example.mmanalog.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate filmExpirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}

