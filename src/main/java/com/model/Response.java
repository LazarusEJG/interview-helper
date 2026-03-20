package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public abstract class Response implements Commentable {
	private UUID id;
	private UUID author;
	private LocalDateTime publishTime;
	private ArrayList<Comment> replies;
	private int score;
	private boolean flagged = false;
	private boolean removed = false;

	public Response(UUID author) {
		this.author = author;

		id = UUID.randomUUID();
		publishTime = LocalDateTime.now();
		replies = new ArrayList<Comment>();
		score = 0;
	}

	protected Response(UUID id, UUID author, LocalDateTime publishTime, ArrayList<Comment> replies, int score) {
		this.id = id;
		this.author = author;
		this.publishTime = publishTime;
		this.replies = replies;
		this.score = score;
	}

	public void addComment(Comment comment) {
		replies.add(comment);
	}

	public void report() {
		this.flagged = true;
	}

	public void remove() {
		this.removed = true;
	}

	public void upVote() {
		this.score++;
	}

	public void downVote() {
		this.score--;
	}

	public UUID getId() {
		return id;
	}

	public UUID getAuthor() {
		return author;
	}

	public LocalDateTime getPublishTime() {
		return publishTime;
	}

	public ArrayList<Comment> getReplies() {
		return replies;
	}

	public int getScore() {
		return score;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public boolean isRemoved() {
		return removed;
	}

}
