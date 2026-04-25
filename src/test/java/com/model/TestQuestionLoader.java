package com.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;

import com.model.Persistence.QuestionLoader;

public class TestQuestionLoader {

	@Test
	public void testGetEmptyQuestion() {
		ArrayList<Question> users = QuestionLoader.getQuestions();
		assertEquals(users, new ArrayList<>());
	}

	@Test
	public void testGetEmpytyFile() {
		ArrayList<Question> users = QuestionLoader.getQuestions("empty");
		ArrayList<Question> expected = new ArrayList<>();
		assertEquals(expected, users);
	}

	@Test
	public void testGetEmpytyArray() {
		ArrayList<Question> users = QuestionLoader.getQuestions("empty-array");
		ArrayList<Question> expected = new ArrayList<>();
		assertEquals(expected, users);
	}

	@Test
	public void testGetArrayOfEmptyObject() {
		ArrayList<Question> users = QuestionLoader.getQuestions("empty-object");
		ArrayList<Question> expected = new ArrayList<>();
		assertEquals(expected, users);
	}

	@Test
	public void testGetValidQuestion() {
		ArrayList<String> categories = new ArrayList<>();
		categories.add("algorithms");
		categories.add("graph");

		ArrayList<Solution> solutions = new ArrayList<>();
		// solutions.add(new Solution(UUID.fromString("955c97d1-13c9-4e9b-a5b4-d3c12053bb56"),
		// 		UUID.fromString("85baa92d-d5df-4e9b-afea-c870dae6295c"), LocalDateTime.parse("2026-03-29T21:38:19"), 43,
		// 		"solutuon.java", "This solution works by solving the problem", false, new ArrayList<>()));

		ArrayList<Comment> comments = new ArrayList<>();
		comments.add(new Comment(UUID.fromString("ef913643-f345-4d08-a42f-07efbee67cc3"),
				UUID.fromString("a69ef809-247b-41c4-b0a9-b71e623f9483"),
				LocalDateTime.parse("2026-03-29T21:44:19"), 14, new ArrayList<>(), "I do not know how to solve this problem"));

		ArrayList<String> hints = new ArrayList<>();

		ArrayList<Question> expected = new ArrayList<>();
		// expected.add(new Question(UUID.fromString("b372d951-40b2-43d6-86b3-74a993040606"),
		// 		UUID.fromString("25ded9f0-4c0a-43bc-b2a5-6505e6482101"),
		// 		"Given a weighted graph, find the path that reaches every node while minimizing the value",
		// 		"Traveling salesman", 4,
		// 		categories, solutions,
		// 		comments, hints,
		// 		LocalDateTime.parse("2026-03-29T21:31:19"), 210));
		ArrayList<Question> users = QuestionLoader.getQuestions("valid-data");
		assertEquals(expected, users);
	}

	@Test
	public void testGetNoFile() {
		ArrayList<Question> expected = new ArrayList<>();
		ArrayList<Question> users = QuestionLoader.getQuestions("no_file");
		assertEquals(expected, users);
	}
}
