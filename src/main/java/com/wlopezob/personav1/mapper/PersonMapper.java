package com.wlopezob.personav1.mapper;

import com.wlopezob.personav1.model.dto.PersonRequestDto;
import com.wlopezob.personav1.model.dto.PersonResponseDto;
import com.wlopezob.personav1.model.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonEntity requestDtoToEntity(PersonRequestDto personRequestDto);

    PersonResponseDto entityToResponseDto(PersonEntity personEntity);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(PersonRequestDto dto, @MappingTarget PersonEntity entity);
}