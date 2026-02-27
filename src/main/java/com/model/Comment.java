package com.model;

public class Comment extends Response {
    private String content;

    public Comment(User author, String content) {
        super(author);
        this.content = content;
    }

    public String toString() {
        return content;
    }
}
