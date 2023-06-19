package com.example.mmanalog.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public Long id;
    @NotNull(message = "Name cannot be empty")
    public String name;
    @NotNull(message = "username cannot be empty")
    @Column(unique = true)
    public String username;
    @NotNull(message = "Email cannot be empty")
    @Email(message = "Please enter a valid email address")
    @Column(unique = true)
    public String email;
    @NotNull(message = "Password cannot be empty")
    @Length(min = 7, message = "Password should be at least 7 characters long")
    public String password;
    public boolean enabled = true;

    //public Set<String> authorities;
}