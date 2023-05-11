package com.example.mmanalog.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @Email
    @GeneratedValue
    @Column(nullable = false, unique = true)

    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean enabled;

}
