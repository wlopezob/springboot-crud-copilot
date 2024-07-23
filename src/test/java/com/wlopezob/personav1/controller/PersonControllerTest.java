package com.wlopezob.personav1.controller;

import com.wlopezob.personav1.util.Util;
import com.wlopezob.personav1.model.dto.PersonRequestDto;
import com.wlopezob.personav1.model.dto.PersonResponseDto;
import com.wlopezob.personav1.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @Mock
    PersonService personService;

    @InjectMocks
    PersonController personController;

    @Test
    void savePerson() {
        //Arrange
        PersonRequestDto personRequestDto = Util
                .convertJsonToObject("personRequestDtoRequest.json", PersonRequestDto.class);
        PersonResponseDto personResponseDto = Util
                .convertJsonToObject("personResponseDto.json", PersonResponseDto.class);
        Mockito.when(personService.savePerson(Mockito.any(PersonRequestDto.class)))
                .thenReturn(personResponseDto);

        //Act
        personController.savePerson(personRequestDto).subscribe(personResponseDto1 -> {
            //Assert
            assertEquals(personResponseDto, personResponseDto1.getBody());
        });
    }

    @Test
    void getMethodName() {
        assertNotNull(personController.getMethodName("param"));
    }
}