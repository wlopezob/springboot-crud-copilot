package com.wlopezob.personav1.service.impl;

import com.wlopezob.personav1.Util.Util;
import com.wlopezob.personav1.mapper.PersonMapper;
import com.wlopezob.personav1.model.dto.PersonRequestDto;
import com.wlopezob.personav1.model.dto.PersonResponseDto;
import com.wlopezob.personav1.model.entity.PersonEntity;
import com.wlopezob.personav1.repository.PersonRepository;
import com.wlopezob.personav1.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonResponseDto savePerson(PersonRequestDto personRequestDto) {
        validatePerson(personRequestDto);
        PersonEntity personEntity = personMapper.requestDtoToEntity(personRequestDto);
        PersonEntity savedEntity = personRepository.save(personEntity);
        //String save = Util.convertObjectToJson(savedEntity);
        return personMapper.entityToResponseDto(savedEntity);
    }

    private void validatePerson(PersonRequestDto personRequestDto) {
        personRepository.findByDni(personRequestDto.getDni())
                .ifPresent(person -> {
                    throw new RuntimeException("Person with DNI " + personRequestDto.getDni() + " already exists.");
                });
    }
}