package com.revature.models;

public class CheckingAccount extends BankAccount {
	
	private String accountNumber;
	private double balance;
	
	
	public CheckingAccount(String accountNumber, double balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public CheckingAccount() {
		
	}
	
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String string) {
		this.accountNumber = string;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance += balance;
	}
	
	public void withDraw(double amount) {
		this.balance -= amount;
	}


	@Override
	public String toString() {
		return "Checking Account account# = " + accountNumber + ", balance = " + balance + "\n";
	}

    
	
	
	
}
