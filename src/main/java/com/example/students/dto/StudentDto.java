package com.example.students.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {

    public Integer id;

    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Name of an student.", example = "Billy", required = true)
    public String name;

    @NotNull(message = "Surname may not be null")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Surname of an student.", example = "Parker", required = true)
    public String surname;

    @NotNull(message = "Years may not be null")
    @Size(min = 2, max = 32, message = "Years must be between 2 and 32 characters long")
    @Schema(description = "Student years.", example = "35", required = true)
    public int years;


}
