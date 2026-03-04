package com.model;

import com.model.Persistence.UserWriter;
import com.model.Persistence.UserLoader;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Manages addition or removal of users in the json files, as well as user data acquisition.
 * @author tsitnik1
 */
public class UserList {
	private static UserList instance;
	private ArrayList<User> users;

	/**
	 * Constructor for UserList
	 */
	private UserList() {
		users = UserLoader.getUsers();
		instance = this;
	}

	/**
	 * GetInstance for UserList
	 * @return Returns specific instance
	 */
	public static UserList getInstance() {
		if (instance == null) {
			instance = new UserList();
		}
		return instance;
	}

	/**
	 * Acquires user username and password from users.json
	 * @param username Username of user
	 * @param password Password of user
	 * @return Returns null
	 */
	public User getUser(String username, String password) {
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	/**
	 * Acquires user UUID from users.json
	 * @param id UUID of user
	 * @return Returns null
	 */
	public User getUser(UUID id) {
		for (User user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	/**
	 * Manages the addition of a new user to users.json
	 * @param eMail Email of user
	 * @param username Username of user
	 * @param password Password of user
	 * @return whether or not the process of adding a user completed successfully
	 */
	public boolean addUser(String eMail, String username, String password) {
		users.add(new User(eMail, username, password));
		return true; // temporary return statement
	}

	/**
	 * Removes user from users.json
	 * @param user User
	 */
	public void removeUser(User user) {
		users.remove(user);
	}

	/**
	 * Acquires users from users.json
	 * @return Returns users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * Saves the list of users
	 * @param filename Storage location of users
	 */
	public void save() {
		UserWriter.saveUsers(users);
	}
}
