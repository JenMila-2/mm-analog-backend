package com.example.mmanalog.dtos;

import com.example.mmanalog.models.FilmStockInventory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public Long id;
    @NotNull
    public String name;
    @NotBlank
    public String email;
    @NotBlank
    @Size(min=6, max=30)
    public String password;
    public boolean enabled;

    public FilmStockInventory filmStockInventory;
    //public Set<String> authorities;
}