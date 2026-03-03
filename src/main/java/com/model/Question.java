package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Question implements Commentable {
    private UUID id;
    private String title;
    private int difficulty;
    private int score;
    private String content;
    private ArrayList<String> categories;
    private ArrayList<Solution> solutions;
    private ArrayList<Comment> comments;
    private User author;
    private ArrayList<String> hints;
    private LocalDateTime publishTime;

    public Question (User author, String content) {

    }

    public Question(User author, String content, String title,
        ArrayList<String> categories, ArrayList<Solution> solutions,
        ArrayList<Comment> comments, ArrayList<String> hints,
        LocalDateTime publishTime, int score) {

    }

    public void upVote() {

    }

    public void downVote() {

    }

    public void addSolution(User user, Solution solution) {

    }

    public void addComment(Comment comment) {

    }

    public void removeResponse(User user, Response response) {

    }

    public String getHints() {
    return null; //temporary statement
    }

    public ArrayList<Solution> getSolutions() {
     return null; //temporary statement
    }
}
