package com.wlopezob.personav1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonRequestDto {
    private Long id;
    private String dni;
    private String name;
    private String lastname;
    private String fathername;
    private int age;
}
