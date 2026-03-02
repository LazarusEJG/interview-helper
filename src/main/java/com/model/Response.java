package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Response implements Commentable {
    private User author;
    private LocalDateTime publishTime;
    private ArrayList<Comment> replies;
    private int score;
    private boolean flagged = false;
    private boolean removed = false;

    public Response(User author) {
        this.author = author;
        publishTime = LocalDateTime.now();
        replies = new ArrayList<Comment>();
        score = 0;
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

}
