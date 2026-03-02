package com.model;

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

	public Solution(UUID id, User author, String filename, String explanation) {
		super(author);
		this.id = id;
		this.file = filename;
		this.explanation = explanation;
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
