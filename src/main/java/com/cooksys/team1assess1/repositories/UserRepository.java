package com.cooksys.team1assess1.repositories;

import com.cooksys.team1assess1.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // add queries if needed
	boolean existsByCredentialsUsername(String username);

	Optional<User> findByCredentialsUsernameAndDeletedFalse(String username);
}
