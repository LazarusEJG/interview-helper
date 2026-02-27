package com.model;

public interface Commentable {
    void addComment(Comment comment);

    default void upVote() {

    }

    default void downVote() {

    }
}
