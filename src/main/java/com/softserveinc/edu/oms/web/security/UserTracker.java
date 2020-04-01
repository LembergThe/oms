package com.softserveinc.edu.oms.web.security;

/**
 * @author Orest
 * 
 */
public class UserTracker {
	private final static UserTracker USER_TRACKER = new UserTracker();

	private UserTracker() {
	}

	public static UserTracker getInstance() {
		return USER_TRACKER;
	}

	private static Integer loggedInUsers = 0;

	public void trackLogin() {
		loggedInUsers++;
	}

	public void trackLogout() {
		loggedInUsers--;
	}

	public Integer getLoggedInUsers() {
		return loggedInUsers;
	}
}
