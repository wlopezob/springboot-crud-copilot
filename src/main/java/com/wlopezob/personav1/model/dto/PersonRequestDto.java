package com.wlopezob.personav1.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonRequestDto {

    private Long id;

    @NotNull(message = "DNI is required")
    private String dni;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Lastname is required")
    private String lastname;

    private String fathername;
    private int age;
}