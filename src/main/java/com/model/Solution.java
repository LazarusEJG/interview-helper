package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Solution extends Response {
	private UUID id;
	private String file;
	private String explanation;
	private boolean verified = false;

	public Solution(User author, String filename) {
		super(author);
		this.file = filename;
	}

	public Solution(UUID id, User author, LocalDateTime publishTime, int score, String filename, String explanation,
			boolean verified, ArrayList<Comment> comments) {
		super(author, publishTime, comments, score);
		this.id = id;
		this.file = filename;
		this.explanation = explanation;
		this.verified = verified;
	}

	public void verify() {
		this.verified = true;
	}

	public UUID getId() {
		return id;
	}

	public String getFile() {
		return file;
	}

	public String getExplanation() {
		return explanation;
	}

	public boolean isVerified() {
		return verified;
	}
}
