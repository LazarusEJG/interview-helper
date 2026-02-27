package com.model;

import java.util.UUID;

public class Solution extends Response {
	private UUID id;
	private String file;
	private String explanation;
	private boolean verified;

	public Solution(User author, String filename) {

	}

	public Solution(UUID id, User author, String filename, String explanation) {

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
