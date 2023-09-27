package com.cooksys.team1assess1.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HashtagDto {
	private String label;

	private Timestamp firstUsed;

	private Timestamp lastUsed;
}
