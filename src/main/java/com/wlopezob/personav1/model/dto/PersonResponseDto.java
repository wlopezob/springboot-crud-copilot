package com.wlopezob.personav1.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonResponseDto {

    private Long id;
    private String dni;
    private String name;
    private String lastname;
    private String fathername;
    private int age;
}