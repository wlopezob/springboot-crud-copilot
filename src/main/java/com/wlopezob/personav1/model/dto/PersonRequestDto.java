package com.wlopezob.personav1.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonRequestDto {

    private Long id;

    @NotBlank(message = "DNI is required")
    private String dni;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Last name is required")
    private String lastname;

    private String fathername;

    private int age;
}
