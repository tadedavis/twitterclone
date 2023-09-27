package com.cooksys.team1assess1.repositories;

import com.cooksys.team1assess1.entities.Tweet;
import com.cooksys.team1assess1.entities.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

	List<Tweet> findByAuthorInAndDeletedFalseOrderByPostedDesc(List<User> authors);

	Tweet findByInReplyTo(Tweet tweet);

	List<Tweet> findByInReplyToOrderByPosted(Tweet tweet);
}
