package com.example.mmanalog.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public Long id;

    @NotBlank
    public String name;

    @NotBlank
    @Email
    public String email;

    @NotBlank
    @Size(min=6, max=30)
    public String password;

    @NotBlank
    public boolean enabled;

    public List<String> authorities;

}

// public Set<Authority> getAuthorities() { return authorities }
// public void setAuthorities(Set<Authority> authorities) { this.authorities = authorities; }