package com.revature.services;

import java.util.List;

import com.revature.models.Transactions;
import com.revature.models.Users;

public interface CustService {
	  		
	public void applyNewAccountWithBalance(Users customer, double balance);
	
	//public boolean transferMoney(String email, double amount);
	public boolean acceptMoneyTransfer(Transactions transaction);
}
