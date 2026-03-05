package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Comment extends Response {
	private String content;

	public Comment(UUID id, UUID author, LocalDateTime publishTime, int score, ArrayList<Comment> replies,
			String content) {
		super(id, author, publishTime, replies, score);
		this.content = content;
	}

	public String toString() {
		return content;
	}

	public String getContent() {
		return content;
	}
}
