package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Solution extends Response {
	private String file;
	private String explanation;
	private boolean verified = false;

	public Solution(UUID author, String explanation, String filename) {
		super(author);
		this.explanation = explanation;
		this.file = filename;
	}

	public Solution(UUID id, UUID author, LocalDateTime publishTime, int score, String filename, String explanation,
			boolean verified, ArrayList<Comment> comments) {
		super(id, author, publishTime, comments, score);
		this.file = filename;
		this.explanation = explanation;
		this.verified = verified;
	}

	public void verify() {
		this.verified = true;
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
