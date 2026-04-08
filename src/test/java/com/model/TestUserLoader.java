package com.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;

import com.model.Persistence.UserLoader;

public class TestUserLoader {

	@Test
	public void testGetEmptyAccounts() {
		ArrayList<User> users = UserLoader.getUsers();
		assertEquals(users, new ArrayList<>());
	}

	@Test
	public void testGetEmpytyFile() {
		ArrayList<User> users = UserLoader.getUsers("empty");
		ArrayList<User> expected = new ArrayList<>();
		assertEquals(expected, users);
	}

	@Test
	public void testGetEmpytyArray() {
		ArrayList<User> users = UserLoader.getUsers("empty-array");
		ArrayList<User> expected = new ArrayList<>();
		assertEquals(expected, users);
	}

	@Test
	public void testGetArrayOfEmptyObject() {
		ArrayList<User> users = UserLoader.getUsers("empty-object");
		ArrayList<User> expected = new ArrayList<>();
		assertEquals(expected, users);
	}

	@Test
	public void testGetValidUser() {
		ArrayList<User> expected = new ArrayList<>();
		expected.add(new User(UUID.fromString("a369c8ec-e2cf-434e-9f38-2aff45da4866"), UserType.REGISTERED, "mail@mail.com",
				"bobusername", "Password!2#", new ArrayList<>(), 0, 0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
				new ArrayList<>(), LocalDate.parse("2026-03-26"), 0));
		ArrayList<User> users = UserLoader.getUsers("valid-data");
		assertEquals(expected, users);
	}

	@Test
	public void testGetNoFile() {
		ArrayList<User> expected = new ArrayList<>();
		ArrayList<User> users = UserLoader.getUsers("no_file");
		assertEquals(expected, users);
	}
}
