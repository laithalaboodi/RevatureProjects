package com.revature.models;

public class SavingAccount extends BankAccount{
	
	private String accountNumber;
	private double balance;
	
	
	public SavingAccount(String accountNumber, double balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public SavingAccount() {
		
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
		return "Saving Account [accountNumber=" + accountNumber + ", balance=" + balance + "]\n";
	}

	
	
}
