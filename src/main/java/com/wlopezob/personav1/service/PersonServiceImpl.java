package com.wlopezob.personav1.service;

import com.wlopezob.personav1.mapper.PersonMapper;
import com.wlopezob.personav1.model.dto.PersonRequestDto;
import com.wlopezob.personav1.model.dto.PersonResponseDto;
import com.wlopezob.personav1.model.entity.PersonEntity;
import com.wlopezob.personav1.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonResponseDto savePerson(PersonRequestDto personRequestDto) {
        personRepository.findByDni(personRequestDto.getDni())
                .ifPresent(personEntity -> {
                    log.error("DNI already exists");
                    throw new RuntimeException("DNI already exists");
                });
        PersonEntity personEntity = personMapper.requestDtoToEntity(personRequestDto);
        PersonEntity savedEntity = personRepository.save(personEntity);
        return personMapper.entityToResponseDto(savedEntity);
    }

    @Override
    public PersonResponseDto updatePerson(Long id, PersonRequestDto personRequestDto) {
        return null;
    }

    @Override
    public void deletePerson(Long id) {

    }
}
