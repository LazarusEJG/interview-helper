package com.model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class InterviewAppUI {
	private InterviewApp library;
	private Scanner keyboard;
	private int terminalWidth;
	private int itemCount;
	private static final String CLEAR = "\033[H\033[2J\033[3J";

	InterviewAppUI() {
		library = new InterviewApp();
	}

	class Options {
		public static final int LOGIN = 1;
		public static final int CREATE_ACCOUNT = 2;
		public static final int SHOW_ALL_USERS = 4;
		public static final int SHOW_MY_ACCOUNT = 5;
		public static final int LOGOUT = 6;

		public static final int SHOW_ALL_QUESTIONS = 3;
		public static final int CREATE_QUESTION = 16;
		public static final int SEARCH_QUESTIONS = 15;
		public static final int VIEW_CURRENT_QUESTION = 7;
		public static final int UPVOTE_CURRENT_QUESTION = 8;
		public static final int DOWNVOTE_CURRENT_QUESTION = 9;

		public static final int VIEW_CURRENT_QUESTION_COMMENTS = 10;
		public static final int ADD_COMMENT_TO_QUESTION = 11;
		public static final int VIEW_CURRENT_QUESTION_SOLUTIONS = 12;

		public static final int REPLY_TO_COMMENT = 13;
		public static final int UPVOTE_SOLUTION = 14;
		public static final int DOWNVOTE_SOLUTION = 15;

		public static final int EXIT = -1;
		public static final int INVALID = 0;
	}

	class QuestionFields {
		public static final int CONTENT = 1;
		public static final int EXAMPLE = 2;

		public static final int EXIT = -1;
		public static final int INVALID = 0;
	}

	public void showOptions() {
		boolean loggedIn = library.getCurrentUser() != null;
		boolean currentQuestion = library.getCurrentQuestion() != null;
		System.out.println(Options.LOGIN + ": Login");
		System.out.println(Options.CREATE_ACCOUNT + ": Create account");
		System.out.println(Options.SHOW_ALL_USERS + ": Show all users");
		if (loggedIn) {
			System.out.println(Options.SHOW_MY_ACCOUNT + ": Show my account");
			System.out.println(Options.LOGOUT + ": Logout");
		}
		System.out.println(Options.SHOW_ALL_QUESTIONS + ": Show all questions");
		if (loggedIn && library.getCurrentUser().getType() == UserType.CONTRIBUTOR) {
			System.out.println(Options.CREATE_QUESTION + ": Create question");
		}

		horizontalRule('.');
		if (currentQuestion) {
			System.out.println(Options.VIEW_CURRENT_QUESTION + ": View current question");
			System.out.println(Options.VIEW_CURRENT_QUESTION_COMMENTS + ": View current question comments");
			System.out.println(Options.VIEW_CURRENT_QUESTION_SOLUTIONS + ": View current question solutions");
			System.out.println(Options.UPVOTE_SOLUTION + ": Upvote a solution");
			System.out.println(Options.ADD_COMMENT_TO_QUESTION + ": Add comment to question");
			System.out.println(Options.REPLY_TO_COMMENT + ": Reply to comment");
			// System.out.println(Options. + ": ");
		}
		System.out.println(Options.EXIT + ": Exit");
	}

	int getOption() {
		String input = keyboard.nextLine();
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return Options.INVALID;
		}
	}

	int selectItem() {
		System.out.print("Select from the above ( 1 - " + itemCount + "; -1 to cancel): ");
		while (true) {
			String input = keyboard.nextLine();
			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println(input + " is not a valid entry. ");
			}
		}
	}

	void horizontalRule(char fillChar) {
		for (int i = 0; i < terminalWidth; i++) {
			System.out.print(fillChar);
		}
		System.out.println();
	}

	public void run() {
		terminalWidth = Integer.parseInt(System.getenv().getOrDefault("COLUMNS", "80"));
		System.out.println("Welcome to InterviewApp!");
		showOptions();
		keyboard = new Scanner(System.in);
		int option = getOption();

		while (option != Options.EXIT) {
			System.out.print(CLEAR);
			System.out.flush();
			int question;
			switch (option) {

				case Options.LOGIN:
					login();
					break;

				case Options.CREATE_ACCOUNT:
					createAccount();
					break;

				case Options.SHOW_ALL_QUESTIONS:
					showAllQuestions();
					question = selectItem();
					library.setCurrentQuestion(library.getSearchResults().get(question - 1));
					break;

				case Options.SEARCH_QUESTIONS:
					searchQuestions();
					question = selectItem();
					library.setCurrentQuestion(library.getSearchResults().get(question - 1));
					break;

				case Options.VIEW_CURRENT_QUESTION:
					viewCurrentQuestion();
					break;

				case Options.VIEW_CURRENT_QUESTION_COMMENTS:
					printComments(library.getCurrentQuestion().getComments());
					break;

				case Options.VIEW_CURRENT_QUESTION_SOLUTIONS:
					printSolutions(library.getCurrentQuestion().getSolutions());
					break;

				case Options.UPVOTE_SOLUTION:
					printSolutions(library.getCurrentQuestion().getSolutions());
					itemCount = library.getCurrentQuestion().getSolutions().size(); // TODO
					question = selectItem();
					library.upvote(library.getCurrentQuestion().getSolutions().get(question - 1));
					break;

				case Options.SHOW_ALL_USERS:
					showAllUsers();
					break;

				case Options.CREATE_QUESTION:
					createQuestion();
					break;

				case Options.SHOW_MY_ACCOUNT:
					showCurrentUser();
					break;

				case Options.LOGOUT:
					logOut();
					break;
			}

			horizontalRule('⠿');

			showOptions();
			option = getOption();
		}
	}

	void login() {
		System.out.println("Enter username:");
		String username = keyboard.nextLine();
		System.out.println("Enter password:");
		String password = keyboard.nextLine();

		if (library.containsUser(username, password)) {
			library.login(username, password);
			System.out.println("Logged in as " + username);
		} else {
			System.out.println("Account not found");
		}
	}

	void createAccount() {
		System.out.println("Enter email:");
		String email = keyboard.nextLine();
		System.out.println("Enter username:");
		String username = keyboard.nextLine();
		System.out.println("Enter password:");
		String password = keyboard.nextLine();

		if (library.isValidEmail(email) == false) {
			System.out.println("Invalid email");
		}
		if (library.isValidUsername(username) == false) {
			System.out.println("Invalid username");
		}
		if (library.isValidPassword(password) == false) {
			System.out.println("Invalid password");
		}

		if (library.isValidEmail(email) && library.isValidPassword(password) &&
				library.isValidUsername(username))
			library.registerUser(email, username, password);
	}

	void printSolution(Solution solution, int number) {
		System.out.println(
				number + ". " + library.getUser(solution.getAuthor()).getUsername() + "(" + solution.getScore() + ")" + "["
						+ solution.getPublishTime() + "]");
		System.out.println(solution.getExplanation());
		System.out.println(solution.getFile());
	}

	void printSolutions(ArrayList<Solution> solutions) {
		int number = 1;
		for (Solution solution : solutions) {
			printSolution(solution, number);
			number++;
		}
	}

	void printComment(Comment comment, int depth, int number) {
		String indent = "  ";
		System.out.println(
				indent.repeat(depth) + number + ". " + library.getUser(comment.getAuthor()).getUsername() + "("
						+ comment.getScore() + ")");
		// ensure that any breaks in the comment are correctly indented
		System.out.println(indent.repeat(depth + 1) + comment.getContent().replace("\n", "\n" + indent));
	}

	void printComments(ArrayList<Comment> comments, int depth, Integer number) {
		for (Comment comment : comments) {
			printComment(comment, depth, number);
			number++;
			printComments(comment.getReplies(), depth + 1, number);
		}
	}

	void printComments(ArrayList<Comment> comments) {
		System.out.println("sdhfjdgsbvjfkshf" + comments.size());
		printComments(comments, 0, 1);
	}

	void printQuestions(ArrayList<Question> questions, boolean numbered) {
		itemCount = questions.size();
		int number = 1;
		for (Question question : questions) {
			if (numbered) {
				System.out.print(number + ". ");
				number++;
			}
			printQuestion(question);
		}
	}

	void printQuestion(Question question) {
		System.out.println(question.getTitle());
		System.out.println(question.getContent());
		System.out.println();
	}

	void createQuestion() {
		User author = library.getCurrentUser();
		if (author != null && author.getType() != UserType.CONTRIBUTOR) {
			System.out.println("You are not a contributor");
			return;
		}

		System.out.println("What is your question title?");
		String title = keyboard.nextLine();

		String content = "";
		ArrayList<String> examples = new ArrayList<>();

		System.out.println("What kind of field do you wish to create?");
		int option = getQuestionField();
		String line;
		while (option != QuestionFields.EXIT) {
			switch (option) {
				case QuestionFields.CONTENT:
					System.out.println("Type the content of the question, when done, type EOF on its own line.");
					line = keyboard.nextLine();
					while (line.equals("EOF") == false) {
						content += '\n' + line;
						line = keyboard.nextLine();
					}
					break;

				case QuestionFields.EXAMPLE:
					String example = "";
					System.out.println("Type the content of the example of the question, when done, type EOF on its own line.");
					line = keyboard.nextLine();
					while (line.equals("EOF") == false) {
						example += '\n' + line;
						line = keyboard.nextLine();
					}
					examples.add(example);
					break;

				default:
					break;
			}

			for (int i = 0; i < examples.size(); i++) {
				content += '\n' + "Example " + i + examples.get(i);
			}
			System.out.println("What kind of field do you wish to create?");
			option = getQuestionField();
		}

		library.addQuestion(author, title, content);
	}

	int getQuestionField() {
		System.out.println(QuestionFields.CONTENT + ". Content");
		System.out.println(QuestionFields.EXAMPLE + ". Example");
		// TODO preview
		System.out.println(QuestionFields.EXIT + ". EXIT");

		String input = keyboard.nextLine();
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return QuestionFields.INVALID;
		}
	}

	void showAllQuestions() {
		printQuestions(library.getAllQuestions(), true);
	}

	void searchQuestions() {
		ArrayList<String> tagFilter;
		int minDifficulty;
		int maxDifficulty;
		boolean onlySolved;
		ArrayList<UUID> authors;
		// TODO
		// printQuestions(library.getQuestions(tagFilter, minDifficulty, maxDifficulty,
		// onlySolved, authors), false);
	}

	void showAllUsers() {
		for (User user : library.getAllUsers()) {
			System.out.println(user.getUsername());
			System.out.println();
		}
	}

	void showCurrentUser() {
		if (library.isLoggedIn()) {
			System.out.println(library.getCurrentUser().toString());
		} else {
			System.out.println("Please login to see your account");
		}
	}

	void viewCurrentQuestion() {
		Question question = library.getCurrentQuestion();
		if (question == null) {
			return;
		}
		printQuestion(question);
	}

	void logOut() {
		if (library.logout() == true) {
			System.out.println("Logout successful");
		} else {
			System.out.println("Logout failed or not logged in");
		}
	}

	public static void main(String[] args) {
		InterviewAppUI libraryInterface = new InterviewAppUI();
		libraryInterface.run();

	}
}
