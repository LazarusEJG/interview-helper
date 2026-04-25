package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Class representing a Solution to a Question
 */

public class Solution extends Response {
	private String file;
	private String explanation;
	private String code;
	private boolean verified = false;

	private static final int VERIFICATION_THRESHOLD = 80;

	public Solution(UUID author, String explanation, String filename, String code) {
		super(author);
		this.explanation = explanation;
		this.code = code;
		this.file = filename;
	}

	public Solution(UUID id, UUID author, LocalDateTime publishTime, int score, String filename, String explanation,
			String code,
			boolean verified, ArrayList<Comment> comments) {
		super(id, author, publishTime, comments, score);
		this.file = filename;
		this.explanation = explanation;
		this.code = code;
		this.verified = verified;
	}

	@Override
	public void upVote() {
		super.upVote();

		if (!verified && getScore() >= VERIFICATION_THRESHOLD) {
			verified = true;
		}
	}

	@Override
	public void downVote() {
		super.downVote();

		if (verified && getScore() < VERIFICATION_THRESHOLD) {
			verified = false;
		}
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

	public String getCode() {
		return code;
	}

	public boolean isVerified() {
		return verified;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof Solution)) {
			return false;
		}

		Solution other = (Solution) o;

		if (super.equals(other) == false) {
			return false;
		}

		if (this.file.equals(other.file) == false) {
			return false;
		}
		if (this.explanation.equals(other.explanation) == false) {
			return false;
		}
		if (this.code.equals(other.code) == false) {
			return false;
		}
		if (this.verified != other.verified) {
			return false;
		}
		return true;
	}
}
