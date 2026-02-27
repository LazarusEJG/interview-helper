package com.model;

import java.util.ArrayList;

public class QuestionList {
    private ArrayList<Question> questions;
    private ArrayList<String> categoryList; //Wasn't sure where to add a stub for this at
    private static QuestionList instance;
    private Question dailyQuestion;
    private Question currentQuestion; //added for setCurrentQuestion

    private QuestionList() {
        questions = QuestionLoader.getQuestions();
    }

    public QuestionList getInstance() {
        if(instance == null) {
            instance = new QuestionList();
        } 
        return instance; //temporary statement
    }

    public Question getDailyQuestion() {
        return dailyQuestion; //temporary statement
    }

    public Question setCurrentQuestion(Question question) {
        return currentQuestion; //temporary statement
    }

    public boolean addQuestion(User author, String content) {
        return true; //temporary statement
    }

    public void removeQuestion(Question question) {

    }

    public ArrayList<Question> getQuestions() {
        return questions; //temporary statement
    }

    public ArrayList<Question> getQuestions(ArrayList<String> tagFilter, int minDifficulty, int maxDifficulty,
        boolean onlySolved, ArrayList<User> authors) {
            return questions; //temporary statement
    }

    public void save(String filename) {

    }
}
