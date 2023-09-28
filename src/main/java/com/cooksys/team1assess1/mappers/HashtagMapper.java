package com.cooksys.team1assess1.mappers;

import org.mapstruct.Mapper;

import com.cooksys.team1assess1.dtos.HashtagDto;
import com.cooksys.team1assess1.entities.Hashtag;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HashtagMapper {
    HashtagDto entityToDto(Hashtag entity);

    List<HashtagDto> entitiesToDtos(List<Hashtag> entities);

    Hashtag dtoToEntity(HashtagDto hashtagDto);
}
