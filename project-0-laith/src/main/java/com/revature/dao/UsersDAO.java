package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.exception.InternalException;
import com.revature.exception.UserNotFound;
import com.revature.models.CheckingAccount;
import com.revature.models.SavingAccount;
import com.revature.models.Users;

public interface UsersDAO {
       
	   
	   public Users createCustomerAccount(Users user, double balance);
	   public Users findOne(String email, String password, boolean isCustomer) 
			   throws UserNotFound, InternalException, SQLException;
       
	   public List<Users> findPendingCustomer()
	   throws InternalException, SQLException;
	   public List<Users> findAll();
       public boolean acceptOne(Users user,CheckingAccount ca,SavingAccount sa);
       public boolean rejectOne(Users user); 
       List<Object> findCustomerInfoByEmail(Users user) throws InternalException;
       
}
