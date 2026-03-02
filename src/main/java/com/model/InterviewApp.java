package com.model;

import java.util.ArrayList;

public class InterviewApp {

    private QuestionList questionList;
    private UserList userList;
    private User currentUser;
    private Question currentQuestion;

     // Constructor 1
    public InterviewApp(QuestionList questionList, UserList userList) {
        this.questionList = questionList;
        this.userList = userList;
    }

    // Constructor 2
    public InterviewApp(User user, QuestionList questionList, UserList userList) {
        this.currentUser = user;
        this.questionList = questionList;
        this.userList = userList;
    }

     //  Methods without implementation

    public User login(String username, String password) {
        return null;
    }

    public boolean logout() {
        return false;
    }

    public void registerUser(String eMail, String username, String password) {

    }

    public void addQuestion(Question question) {

    }

    public ArrayList<Question> getQuestions() {
        return null;
    }

    public Question getDailyQuestion() {
        return null;
    }

    public Question setCurrentQuestion(Question question) {
        return null;
    }

    public void bookmarkQuestion(Question question) {

    }

    public ArrayList<Question> getBookmarkedQuestions(User currentUser) {
        return null;
    }

    public ArrayList<Question> getAnsweredQuestions(User currentUser) {
        return null;
    }

    public String getHints() {
        return null;
    }

    public void addSolution(User currentUser, Solution solution) {

    }

    public ArrayList<Solution> getSolutions() {
        return null;
    }

    public void addComment(Commentable parent, Comment comment) {

    }

    public void report() {

    }

    public void upvote(Commentable content) {

    }

    public void downvote(Commentable content) {

    }
}
