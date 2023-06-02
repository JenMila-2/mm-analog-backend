package com.example.mmanalog.dtos.InputDtos;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagInputDto {

    public Long id;
    @Size(min = 3, message = "The tag must contain at least 3 characters.")
    public String tagName;
}
