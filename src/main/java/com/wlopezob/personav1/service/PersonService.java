package com.wlopezob.personav1.service;

import com.wlopezob.personav1.model.dto.PersonRequestDto;
import com.wlopezob.personav1.model.dto.PersonResponseDto;

import java.util.List;

public interface PersonService {

    PersonResponseDto savePerson(PersonRequestDto personRequestDto);

//    default PersonResponseDto updatePerson(Long id, PersonRequestDto personRequestDto) { return null; };
//
//    default void deletePerson(Long id){};
//
//    default List<PersonResponseDto> listPerson(){ return null; };
}