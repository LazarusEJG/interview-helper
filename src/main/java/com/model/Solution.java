package com.model;

public class Solution extends Response {
    private String file;
    private String explanation;
    private boolean verified = false;

    public Solution(User author, String filename) {
        super(author);
        this.file = filename;
    }

    public Solution(User author, String filename, String explanation) {
        super(author);
        this.file = filename;
        this.explanation = explanation;
    }

    public void verify() {
        this.verified = true;
    }
}