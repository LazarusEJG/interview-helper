package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Comment extends Response {
	private String content;

	public Comment(UUID author, String content) {
		super(author);
		this.content = content;
	}

	/**
	 * Comment constructor
	 * @param id Id of the comment
	 * @param author Id of the author of the comment
	 * @param publishTime time the comment was published
	 * @param score upvote and downvote score
	 * @param replies the replies of the comment
	 * @param content content within the comment
	 */
	public Comment(UUID id, UUID author, LocalDateTime publishTime, int score, ArrayList<Comment> replies,
			String content) {
		super(id, author, publishTime, replies, score);
		this.content = content;
	}

	/**
	 * toString to return the content of the comment
	 */
	@Override
	public String toString() {
		return "Commentor: "+ author + "\n" +
		"Publish Time: " + publishTime + "  " + "Score: " + score + 
		"Comment:" + content + "\n";
	}

	/**
	 * method to get the content of the comment
	 * @return
	 */
	public String getContent() {
		return content;
	}
}
