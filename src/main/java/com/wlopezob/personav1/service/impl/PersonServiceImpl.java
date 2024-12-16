package com.wlopezob.personav1.service.impl;

import com.wlopezob.personav1.Util.Util;
import com.wlopezob.personav1.config.UtilCustom;
import com.wlopezob.personav1.config.properties.LwpApiProperties;
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
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final UtilCustom utilCustom;
    private final LwpApiProperties lwpApiProperties;

    @Override
    public PersonResponseDto savePerson(PersonRequestDto personRequestDto) {
        log.info("Saving person: {}", personRequestDto);
        validatePerson(personRequestDto);
        PersonEntity personEntity = personMapper.requestDtoToEntity(personRequestDto);
        PersonEntity savedEntity = personRepository.save(personEntity);
        log.info("Person saved: {}", savedEntity);
        return personMapper.entityToResponseDto(savedEntity);
    }

    private void validatePerson(PersonRequestDto personRequestDto) {
        personRepository.findByDni(personRequestDto.getDni())
                .ifPresent(person -> {
                    log.error("Person with DNI {} already exists.", personRequestDto.getDni());
                    throw new RuntimeException("Person with DNI " + personRequestDto.getDni() + " already exists.");
                });
    }

    @Override
    public Flux<PersonResponseDto> listPerson() {
        return WebClient.builder()
            .build().get()
            .uri( lwpApiProperties.getApis().getUrlDataPersona()+"/person/lista")
            //.headers(utilCustom.headersMdcConsumer())
            .retrieve()
            .bodyToFlux(PersonResponseDto.class);
    }

    @Override
    public Mono<PersonResponseDto> savePersonv2(PersonRequestDto personRequestDto) {
        return WebClient.builder()
            .build().post()
            .uri( lwpApiProperties.getApis().getUrlDataPersona()+"/person")
            .bodyValue(personRequestDto)
            .headers(utilCustom.headersMdcConsumer())
            .retrieve()
            .bodyToMono(PersonResponseDto.class);
    }
}