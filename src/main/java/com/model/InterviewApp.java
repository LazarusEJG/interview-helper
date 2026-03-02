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

    //  Methods Stubs

    // Sets Current User to a user for given list and returns the user.
    public User login(String username, String password) {
        currentUser = userList.getUser(username, password);
        return currentUser;
    } 

    // Checks if there is a current user and logs out by setting current user to null. Returns true if logout is successful, false otherwise.
    public boolean logout() {
        if (currentUser != null) {
            currentUser = null;
            return true;
        }
        return false;
    }

    // Registers a new user by adding them to list with email, username and password.
    public void registerUser(String eMail, String username, String password) {
        userList.addUser(eMail, username, password);
    }

    // Adds Question to questionList with author and content.
    public void addQuestion(Question question) {
        questionList.addQuestion(question.getAuthor(), question.getContent());
    }

    // Overloaded getQuestions method that takes in filters and returns a list of questions that match the filters.
    public ArrayList<Question> getQuestions(
        ArrayList<String> tagFilter,
        Integer minDifficulty,
        Integer maxDifficulty,
        boolean onlySolved,
        ArrayList<User> authors) {

    return questionList.getQuestions(
            tagFilter,
            minDifficulty,
            maxDifficulty,
            onlySolved,
            authors
    );
}

    // getter for question list
    public ArrayList<Question> getQuestions() {
        return questionList.getQuestions();
    }

    // getter for daily question
    public Question getDailyQuestion() {
        return questionList.getDailyQuestion();
    }

    // Sets current question to a question and returns the current question.
    public Question setCurrentQuestion(Question question) {
        currentQuestion = questionList.setCurrentQuestion(question);
        return currentQuestion;
    }

    // Checks if there is a current user and bookmarks a question for the user.
    public void bookmarkQuestion(Question question) {
        if (currentUser != null) {
            currentUser.bookmarkQuestion(question);
        }
    }

    // Getter for Bookmarked Questions for the current user.
    public ArrayList<Question> getBookmarkedQuestions(User currentUser) {
        return currentUser.getBookmarkedQuestions();
    }

    // Getter for Answered Questions for the current user.
    public ArrayList<Question> getAnsweredQuestions(User currentUser) {
       return currentUser.getAnsweredQuestions();
    }

    // Checks if there is a currentQuestion and if the question has hints.
    //  Returns the first hint. Otherwise, returns an empty string.
    public String getHints() {
        if (currentQuestion != null && !currentQuestion.getHints().isEmpty()) {
            return currentQuestion.getHints().get(0);
        }
        return "";
    }

    // Checks if there is a currentQuestion and adds a solution
    // with currentUser and the solution.
    public void addSolution(User currentUser, Solution solution) {
        if (this.currentQuestion != null) {
            this.currentQuestion.addSolution(currentUser, solution);
        }
    }

    // Checks for currentQuestion and returns the list of solutions. 
    // If there is no currentQuestion, returns an empty list.
    public ArrayList<Solution> getSolutions() {
        if (currentQuestion != null) {
            return currentQuestion.getSolutions();
        }
        return new ArrayList<>();
    }

    // Getter for bookmarked solutions.
    public ArrayList<Solution> getBookmarkedSolutions(User currentUser) {
        return currentUser.getBookmarkedSolutions();
    }

    // Getter for submitted solutions.
    public ArrayList<Solution> getSubmittedSolutions(User currentUser) {
        return currentUser.getSubmittedSolutions();
    }   

    // Adds comment to the parent content (question or solution) using a comment.
    public void addComment(Commentable parent, Comment comment) {
        parent.addComment(comment);
    }

    // Changed from UML to take in Response to uphold the facade design.
    public void report(Response response) {
        response.report();
    }

    // Adds an upvote to the content
    public void upvote(Commentable content) {
        content.upVote();
    }

    // Downvotes given content.
    public void downvote(Commentable content) {
        content.downVote();
    }
}
