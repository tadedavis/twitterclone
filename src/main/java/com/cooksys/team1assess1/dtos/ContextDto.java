package com.cooksys.team1assess1.dtos;

import com.cooksys.team1assess1.entities.Tweet;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ContextDto {
    private Tweet target;
    private List<Tweet> before;
    private List<Tweet> after;
}
