package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Response implements Commentable {
    private User author;
    private LocalDateTime publishTime;
    private ArrayList<Comment> replies;
    private int score;

    public Response(User author) {

    }

    public void addComment(Comment comment) {

    }

    public void report() {

    }

    public void remove() {

    }

    public void upVote() {
        
    }

    public void downVote() {
        
    }

}
