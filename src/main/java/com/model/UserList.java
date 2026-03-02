package com.model;

import com.model.Persistence.UserWriter;

import java.util.ArrayList;
import java.util.UUID;

public class UserList {
	private static UserList instance;
	private ArrayList<User> users;

	private UserList() {
		users = new ArrayList<User>();
		instance = this;
	}

	public static UserList getInstance() {
		if (instance == null) {
			instance = new UserList();
		}
		return instance;
	}

	public User getUser(String username, String password) {
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	public User getUser(UUID id) {
		for (User user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	public boolean addUser(String eMail, String username, String password) {
		users.add(new User(eMail, username, password));
		return true; // temporary return statement
	}

	public void removeUser(User user) {
		users.remove(user);
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void save(String filename) {
		UserWriter.saveUsers(users);
	}
}
