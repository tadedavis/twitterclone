package com.cooksys.team1assess1.mappers;

import com.cooksys.team1assess1.dtos.ProfileDto;
import com.cooksys.team1assess1.entities.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDto dtoToEntity(Profile entity);

    Profile entityToDto(ProfileDto profileDto);
}
