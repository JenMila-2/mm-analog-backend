package com.example.mmanalog.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {

    public Long id;

    @NotBlank
    public String name;

    @NotBlank
    public String email;

    @Size(min=6, max=20)
    public String password;
    public boolean enabled;

}
