package com.model;

/**
 * Interface for things that comments can be added to
 */

public interface Commentable {
    void addComment(Comment comment);

    default void upVote() {
        
    }

    default void downVote() {

    }
}
