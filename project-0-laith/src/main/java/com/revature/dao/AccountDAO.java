package com.revature.dao;

import com.revature.models.BankAccount;
import com.revature.models.Users;

public interface AccountDAO {
		
	   public BankAccount saveOne(BankAccount newAccount);
	   public BankAccount findOne(Users userId);
	   public boolean updateBalance(int bankId,BankAccount existingAccount);
	   public void updateAccount(BankAccount account);
	  
	   
}
