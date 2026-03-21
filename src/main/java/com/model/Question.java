package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Question implements Commentable {
	private UUID id; // added to constructor because I felt it probably needed to be there
	private String title;
	private int difficulty;
	private int score;
	private String content;
	private ArrayList<String> categories;
	private ArrayList<Solution> solutions;
	private ArrayList<Comment> comments;
	private UUID author;
	private ArrayList<String> hints;
	private LocalDateTime publishTime;

	/**
	 * Constructor for question
	 * 
	 * @param author  UUID of the author of the question
	 * @param content Content of the question
	 */
	public Question(UUID author, String title, String content) {
		this.author = author;
		this.title = title;
		this.content = content;
		publishTime = LocalDateTime.now();

		id = UUID.randomUUID();
		categories = new ArrayList<>();
		solutions = new ArrayList<>();
		comments = new ArrayList<>();
		hints = new ArrayList<>();
	}

	/**
	 * Constructor for question
	 * 
	 * @param id          UUID of the question
	 * @param author      UUID of the author of the question
	 * @param content     the content within the question
	 * @param title       title of the question
	 * @param difficulty  difficulty of the question
	 * @param categories  catagory or categories of the question
	 * @param solutions   solution list of the question
	 * @param comments    comments list of the question
	 * @param hints       hints list on the question
	 * @param publishTime publishtime of the question
	 * @param score       current score of the question
	 */
	public Question(UUID id, UUID author, String content, String title, int difficulty,
			ArrayList<String> categories, ArrayList<Solution> solutions,
			ArrayList<Comment> comments, ArrayList<String> hints,
			LocalDateTime publishTime, int score) {
		this.id = id; // refer to UUID instance variable
		this.author = author;
		this.content = content;
		this.title = title;
		this.difficulty = difficulty;
		this.categories = categories;
		this.solutions = solutions;
		this.comments = comments;
		this.hints = hints;
		this.publishTime = publishTime;
		this.score = score;

	}

	public void upVote() {

	}

	/**
	 * downvoting the question decreases the score
	 */
	public void downVote() {
		score--;
	}

	/**
	 * method to a add a solution to the question
	 * 
	 * @param user     User who made the question
	 * @param solution [placeholder text]
	 *                 [I wanted to add at least somthing if I could.
	 *                 Sorry in advance if this is like super wrong - EJ]
	 */
	public void addSolution(User author, String explanation, String filename) {
		this.solutions.add(new Solution(author.getId(), explanation, filename));
	}

	/**
	 * method to add a comment to the question
	 * [^^^^ sorry if this is super wrong and needs to be changed entirely.]
	 */
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	/**
	 * removes the response of a user from the question
	 * @param user Who's response is removed
	 * @param response The removed response
	 */
	public void removeResponse(User user, Response response) {

	}

	/**
	 * method used to get the hints on a question
	 * @return the list of hints
	 */
	public ArrayList<String> getHints() {
		return hints; // temporary statement & changed to match return types (change if wrong)
	}

	/**
	 * method used to get the solutions on a question
	 * @return the list of solutions
	 */
	public ArrayList<Solution> getSolutions() {
		return solutions; // temporary statement
	}

	/**
	 * ID of the question
	 * @return returns the ID field of the question
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * returns the title field of the question
	 * @return ^^^
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * returns the difficulty field of the question
	 * @return
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * returns the score field of the question
	 * @return
	 */
	public int getScore() {
		return score;
	}

	/**
	 * returns the content within the question
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * returns the list of categories the question has
	 * @return
	 */
	public ArrayList<String> getCategories() {
		return categories;
	}

	/**
	 * returns the list of comments the question has
	 */
	public ArrayList<Comment> getComments() {
		return comments;
	}

	/**
	 * returns the id of the author of the question
	 * @return
	 */
	public UUID getAuthor() {
		return author;
	}

	/**
	 * returns the publishtime of the question
	 * @return
	 */
	public LocalDateTime getPublishTime() {
		return publishTime;
	}

	/**
	 * toString to build the question out
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Question <" + title + ">" + "\n");
		sb.append("id: " + id + "\n");
		sb.append("author: " + author.toString() + "\n");
		sb.append("content: " + content + "\n");
		sb.append("title: " + title + "\n");
		sb.append("difficulty: " + difficulty + "\n");
		sb.append("categories: \n");
		for (String category : categories) {
			sb.append("  " + category + "\n");
		}
		sb.append("solutions: \n");
		for (Solution solution : solutions) {
			sb.append("  " + solution.getId().toString() + "\n");
		}
		sb.append("comments: \n");
		for (Comment comment : comments) {
			sb.append("  " + comment.getId().toString() + "\n");
		}
		sb.append("hints: \n");
		for (String hint : hints) {
			sb.append("  " + hint + "\n");
		}
		sb.append("publishTime: " + publishTime + "\n");
		sb.append("score: " + score + "\n");

		return sb.toString();
	}
}
