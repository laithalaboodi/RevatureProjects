package com.revature.exception;

public class UserNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotFound() {
		super("ERROR: User not found");
	}
}
