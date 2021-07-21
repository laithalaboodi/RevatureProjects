package com.revature.dao;

import java.sql.SQLException;

import com.revature.exception.InternalException;
import com.revature.exception.UserNotFound;
import com.revature.models.CheckingAccount;
import com.revature.models.Customer;
import com.revature.models.SavingAccount;
import com.revature.models.Users;

public class UserDAODebugger {

	public static void main(String[] args) throws UserNotFound, SQLException {
		// TODO Auto-generated method stub
		UsersDAO ud = new UserPostgresDAO();
		Users customer = new Customer();
		customer.setUserId(5);
		
		  CheckingAccount newChequingAccount = new CheckingAccount();
		  SavingAccount	newSavingAccount = new SavingAccount();
		  newChequingAccount.setAccountNumber(generateAccountNumber());
		  newChequingAccount.setBalance(customer.getInitialDeposit());
		  newSavingAccount.setAccountNumber(generateAccountNumber());
		  	  
	
		  System.out.println(ud.rejectOne(customer));


	}
	
	public static String generateAccountNumber() {
		int i =1;
		StringBuffer accountNumber = new StringBuffer("");
		while (true) {
			if(i==7) {
				break;
			}		
			int a = (int) (Math.random() * 10);
			
			accountNumber.append(a);
			i++;
		}
		return accountNumber.toString();
	}

}
