package com.example.mmanalog.dtos;

import com.example.mmanalog.models.Authority;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public Long id;
    @NotNull(message = "Name cannot be empty")
    public String name;

    @NotNull(message = "username cannot be empty")
    @Column(nullable = false, unique = true)
    public String username;

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Please enter a valid email address")
    @Column(unique = true)
    public String email;

    @NotNull(message = "Password cannot be empty")
    @Length(min = 7, message = "Password should be at least 7 characters long")
    @Column(nullable = false)
    public String password;

    public String apikey;

    public boolean enabled = true;

    @JsonSerialize
    public Set<Authority> authorities;

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}