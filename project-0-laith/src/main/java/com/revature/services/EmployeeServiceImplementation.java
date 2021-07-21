package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.dao.UserPostgresDAO;
import com.revature.exception.InternalException;
import com.revature.exception.UserNotFound;
import com.revature.models.CheckingAccount;
import com.revature.models.SavingAccount;
import com.revature.models.Users;

public class EmployeeServiceImplementation implements EmployeesService, UserService {
	
	
	private UserPostgresDAO upd;
	
	public EmployeeServiceImplementation(UserPostgresDAO upd) {
		   this.upd = upd;
	}
	
	

	public Users userLogIn(String email, String password, boolean isCustomer) throws UserNotFound, InternalException, SQLException {
		Users user = upd.findOne(email, password, isCustomer);	
		if(user!= null) {
		   System.out.println("Welcome, "+ user.getFirstName()+" "+user.getLastName());
		   return user;
		}
		else {
			System.out.println(" User is not found");
		}
		return null;

	}

	public boolean setCustomerStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	public void viewCustomerAccount(Users customer) {
		// TODO Auto-generated method stub

	}

	public List<Users> viewListPendingUser() throws InternalException, SQLException {
		List<Users> list = upd.findPendingCustomer();
		if(list.size() != 0) {
			return list;
		}
		
		return null;
	}
	
	public boolean approveCustomer(Users user) {
		
		  CheckingAccount newChequingAccount = new CheckingAccount();
		  SavingAccount	newSavingAccount = new SavingAccount();
		  newChequingAccount.setAccountNumber(generateAccountNumber());
		  newChequingAccount.setBalance(user.getInitialDeposit());
		  newSavingAccount.setAccountNumber(generateAccountNumber());
		  	  
		  return upd.acceptOne(user,newChequingAccount,newSavingAccount);
	}
	
	public boolean rejectCustomer(Users user) {
		  return upd.rejectOne(user);
	}
	
	public String generateAccountNumber() {
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


    //Employee can view customer account using their email
	public List<Object> viewCustomerInfo(Users customer) {
		List<Object> listInfo;
		try {
			listInfo = upd.findCustomerInfoByEmail(customer);	
			if(listInfo.size() != 0) {
				return listInfo;
			}
		} catch (InternalException e) {
			e.printStackTrace();
			System.out.println(e);
		} 

		return null;		
	}

}
