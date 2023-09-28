package com.cooksys.team1assess1.services;

import java.util.List;

import com.cooksys.team1assess1.entities.Hashtag;
import com.cooksys.team1assess1.entities.Tweet;

public interface HashtagService {

	List<Hashtag> getTags();

	List<Tweet> getTweetsWithTag(String label);

}
