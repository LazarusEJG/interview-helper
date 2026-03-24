package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
/**
 * A type of response that is just a comment
 * Can be added to questions, solutions, and other comments
 */
public class Comment extends Response {
	private String content;

	public Comment(UUID author, String content) {
		super(author);
		this.content = content;
	}

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
		return "Commenter: "+ author + "\n" +
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
