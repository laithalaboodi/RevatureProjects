package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.exception.InternalException;
import com.revature.models.Users;

public interface EmployeesService {
    
	public List<Users> viewListPendingUser() throws InternalException, SQLException;
	public boolean setCustomerStatus();
	public void viewCustomerAccount(Users customer);
}
