package com.heyjie.springboot1.mapper;

import com.heyjie.springboot1.dto.PersonDto;
import com.heyjie.springboot1.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper MAPPER = Mappers.getMapper(PersonMapper.class);

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name")
    })
    Person toEntity(PersonDto dto);

    PersonDto toDto(Person entity);
}
