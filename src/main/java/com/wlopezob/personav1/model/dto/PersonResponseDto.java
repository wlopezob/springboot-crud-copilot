package com.wlopezob.personav1.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonResponseDto {

    @Schema(
            description = "Identidicador de la persona",
            type = "long",
            example = "1"
    )
    private Long id;

    @NotBlank(message = "DNI is required")
    @Schema(
            description = "Documento de identidad de la persona",
            type = "string",
            example = "45124512"

    )
    private String dni;

    @NotBlank(message = "Name is required")
    @Schema(
            description = "Nombre de la persona",
            type = "string",
            example = "juan"
    )
    private String name;

    @NotBlank(message = "Last name is required")
    @Schema(
            description = "Apellido de la persona",
            type = "string",
            example = "castillo"
    )
    private String lastname;

    @Schema(
            description = "Apellido del papa",
            type = "string",
            example = "quispe"
    )
    private String fathername;

    @Schema(
            description = "edad de la persona",
            type = "int",
            example = "32"
    )
    private int age;
}