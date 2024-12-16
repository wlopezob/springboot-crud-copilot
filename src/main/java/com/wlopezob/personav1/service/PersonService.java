package com.wlopezob.personav1.service;

import com.wlopezob.personav1.model.dto.PersonRequestDto;
import com.wlopezob.personav1.model.dto.PersonResponseDto;

import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {

    PersonResponseDto savePerson(PersonRequestDto personRequestDto);
    Flux<PersonResponseDto> listPerson();
    Mono<PersonResponseDto> savePersonv2(PersonRequestDto personRequestDto);
}