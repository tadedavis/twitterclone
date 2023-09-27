package com.cooksys.team1assess1.dtos;

import com.cooksys.team1assess1.entities.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class UserResponseDto {
    private String username;
    private ProfileDto profile;
    private Timestamp joined;
}
