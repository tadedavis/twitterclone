package com.cooksys.team1assess1.repositories;

import com.cooksys.team1assess1.dtos.CredentialsDto;
import com.cooksys.team1assess1.entities.Credentials;
import com.cooksys.team1assess1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // add queries if needed
    User findByCredentialsUsername(String username);
    User findByCredentials(Credentials credentials);

	boolean existsByCredentialsUsername(String username);

	Optional<User> findByCredentialsUsernameAndDeletedFalse(String username);

    @Query("SELECT u FROM User u WHERE u.deleted = false")
    List<User> getAllNonDeletedUsers();

}
