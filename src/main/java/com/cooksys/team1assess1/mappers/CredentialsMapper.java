package com.cooksys.team1assess1.mappers;

import com.cooksys.team1assess1.dtos.CredentialsDto;
import com.cooksys.team1assess1.entities.Credentials;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {
    CredentialsDto entityToDto(Credentials entity);

    Credentials dtoToEntity(CredentialsDto credentialsDto);
}
