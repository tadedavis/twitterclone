package com.cooksys.team1assess1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.team1assess1.entities.Hashtag;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
	
}
