package com.wlopezob.personav1.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonRequestDto {

    @Schema(
            description = "Identidicador de la persona",
            type = "long",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long id;

    @NotBlank(message = "DNI is required")
    @Schema(
            description = "Documento de identidad de la persona",
            type = "string",
            example = "45124512",
            requiredMode = Schema.RequiredMode.REQUIRED

    )
    private String dni;

    @NotBlank(message = "Name is required")
    @Schema(
            description = "Nombre de la persona",
            type = "string",
            example = "juan",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    @NotBlank(message = "Last name is required")
    @Schema(
            description = "Apellido de la persona",
            type = "string",
            example = "castillo",
            requiredMode = Schema.RequiredMode.REQUIRED
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
