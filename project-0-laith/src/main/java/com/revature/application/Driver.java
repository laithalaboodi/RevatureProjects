package com.revature.application;

import java.sql.SQLException;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.PostgresDAO;
import com.revature.dao.TransactionPosgresDAO;
import com.revature.dao.UserPostgresDAO;
import com.revature.displays.Menu;
import com.revature.exception.InternalException;
import com.revature.exception.UserNotFound;
import com.revature.services.CustomerServiceImplementation;
import com.revature.services.EmployeeServiceImplementation;


public class Driver {
    
	public static Logger laithLogger = LogManager.getLogger("com.revature.laithLogger");
	public static void main(String[] args) 
			throws UserNotFound, InternalException, 
SQLException, InputMismatchException {
		
		UserPostgresDAO userid = new UserPostgresDAO();
		PostgresDAO poa = new PostgresDAO();
		TransactionPosgresDAO transcationsPD = new TransactionPosgresDAO();
		CustomerServiceImplementation customerServiceImp = new CustomerServiceImplementation(userid,poa,transcationsPD);
		EmployeeServiceImplementation employeesServiceImp = new EmployeeServiceImplementation(userid);
		Menu userMenu = new Menu(customerServiceImp,employeesServiceImp);

		laithLogger.info("Server up!");
		System.out.println("Welcome To Orgrimmar Bank!\n");
		while(true) {
							
			userMenu.manageUserAccountInput();
			System.out.println("\n");
		}
		

	    
	}

}
