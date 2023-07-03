package com.example.mmanalog.repositories;

import com.example.mmanalog.models.FilmStockInventory;
import com.example.mmanalog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmStockInventoryRepository extends JpaRepository<FilmStockInventory, Long> {

    List<FilmStockInventory> findFilmStockInventoryByUser(User user);
}
