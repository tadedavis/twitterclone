package com.cooksys.team1assess1.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name="user_table")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private Credentials credentials;
    @CreationTimestamp
    private Timestamp joined;
    private boolean deleted = false;
    @Embedded
    private Profile profile;
}
