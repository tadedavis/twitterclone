package com.cooksys.team1assess1.mappers;

import org.mapstruct.Mapper;

import com.cooksys.team1assess1.dtos.HashtagDto;
import com.cooksys.team1assess1.entities.Hashtag;

@Mapper(componentModel = "spring")
public interface HashtagMapper {
    HashtagDto entityToDto(Hashtag entity);

    Hashtag dtoToEntity(HashtagDto hashtagDto);
}
