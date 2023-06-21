package com.example.mmanalog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(AuthorityKey.class)
@Table(name = "authorities")
public class Authority implements Serializable {
    @jakarta.persistence.Id
    private Long id;
    @Id
    @Column(nullable = false)
    private String username;
    @Id
    @Column(nullable = false)
    private String authority;
    }

