package com.cooksys.team1assess1.dtos;

import com.cooksys.team1assess1.entities.Credentials;
import com.cooksys.team1assess1.entities.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRequestDto {
    private Credentials credentials;
    private Profile profile;
}
