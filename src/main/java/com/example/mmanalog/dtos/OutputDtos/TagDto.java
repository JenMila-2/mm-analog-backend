package com.example.mmanalog.dtos.OutputDtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {

    @Id
    @GeneratedValue
    public Long id;
    public String tagName;
}
