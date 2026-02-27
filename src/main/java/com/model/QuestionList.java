package com.model;

import java.util.ArrayList;

public class QuestionList {
    private ArrayList<Question> questions;
    private ArrayList<String> categoryList;
    private static Question instance;

    private QuestionList() {
    }

    public QuestionList getInstance() {
        return null; //temporary statement
    }

    public Question getDailyQuestion() {
        return null; //temporary statement
    }

    public Question setCurrentQuestion(Question question) {
        return null; //temporary statement
    }

    public boolean addQuestion(User author, String content) {
        return true; //temporary statement
    }

    public void removeQuestion(Question question) {

    }

    public ArrayList<Question> getQuestions() {
        return null; //temporary statement
    }

    public ArrayList<Question> getQuestions(ArrayList<String> tagFilter, int minDifficulty, int maxDifficulty,
        boolean onlySolved, ArrayList<User> authors) {
            return null; //temporary statement
    }

    public void save(String filename) {

    }
}
