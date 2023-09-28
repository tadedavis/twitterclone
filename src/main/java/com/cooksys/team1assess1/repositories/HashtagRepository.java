package com.cooksys.team1assess1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.team1assess1.entities.Hashtag;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
	boolean existsByLabel(String label);

	Hashtag findByLabel(String label);
}
