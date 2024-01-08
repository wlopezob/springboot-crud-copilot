package com.wlopezob.personav1.service;

import com.wlopezob.personav1.model.dto.PersonRequestDto;
import com.wlopezob.personav1.model.dto.PersonResponseDto;

import java.util.List;

public interface PersonService {

    PersonResponseDto savePerson(PersonRequestDto personRequestDto);

    PersonResponseDto updatePerson(Long id, PersonRequestDto personRequestDto);

    void deletePerson(Long id);

    List<PersonResponseDto> listPerson();
}