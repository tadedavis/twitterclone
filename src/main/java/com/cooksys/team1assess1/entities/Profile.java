package com.cooksys.team1assess1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Profile {
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String email;
    private String phone;

}
