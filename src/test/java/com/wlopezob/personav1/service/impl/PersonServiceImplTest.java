package com.wlopezob.personav1.service.impl;

import com.wlopezob.personav1.mapper.PersonMapperImpl;
import com.wlopezob.personav1.model.dto.PersonRequestDto;
import com.wlopezob.personav1.model.entity.PersonEntity;
import com.wlopezob.personav1.repository.PersonRepository;
import com.wlopezob.personav1.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    PersonRepository personRepository;

    @Spy
    PersonMapperImpl personMapper;

    @InjectMocks
    PersonServiceImpl personService;

    @Test
    void savePersonWhenDniNotFound() {
        //arrange
        PersonRequestDto personRequestDto = Util
                .convertJsonToObject("personRequestDtoRequest.json", PersonRequestDto.class);

        PersonEntity personEntity = Util
                .convertJsonToObject("personEntity.json", PersonEntity.class);
        Mockito.when(personRepository.findByDni(personRequestDto.getDni()))
                .thenReturn(Optional.empty());
        Mockito.when(personRepository.save(Mockito.any()))
                .thenReturn(personEntity);

        //act
        var personEntityResponse = personService.savePerson(personRequestDto);

        //assert
        Assertions.assertEquals(personEntityResponse.getDni(), "12345678");
    }

    @Test
    void savePersonWhenDniFound() {
        //arrange
        PersonRequestDto personRequestDto = Util
                .convertJsonToObject("personRequestDtoRequest.json", PersonRequestDto.class);

        PersonEntity personEntity = Util
                .convertJsonToObject("personEntity.json", PersonEntity.class);
        Mockito.when(personRepository.findByDni(personRequestDto.getDni()))
                .thenReturn(Optional.of(personEntity));
        //assert
        Assertions.assertThrows(RuntimeException.class, () ->
                //act
                personService.savePerson(personRequestDto)
        );


    }
}