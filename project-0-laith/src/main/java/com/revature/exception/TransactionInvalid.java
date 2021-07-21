package com.revature.exception;

public class TransactionInvalid extends Exception {
	private static final long serialVersionUID = 1L;

	public TransactionInvalid() {
		super("Invalid Transaction");
	}
}
