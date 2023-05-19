package com.example.mmanalog.dtos;

import jakarta.validation.constraints.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDto {

    public Long id;
    @NotNull
    @Size(max = 75, message = "Title must be between 0 and 75 characters!")
    public String photoTitle;
    @Size(max=50, message="This field cannot contain more than 50 characters.")
    public String camera;
    @Size(max=50, message="This field cannot contain more than 50 characters.")
    public String filmStock;
    public String filmFormat;
    @Size(max=50, message="This field cannot contain more than 50 characters.")
    public String developedBy;
    public int iso;
    public String fStop;
    public String shutterSpeed;
    public String exposureCompensation;
}
