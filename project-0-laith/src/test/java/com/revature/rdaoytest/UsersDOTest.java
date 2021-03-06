package com.revature.rdaoytest;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.revature.dao.UserPostgresDAO;
import com.revature.dao.UsersDAO;
import com.revature.exception.InternalException;
import com.revature.exception.UserNotFound;
import com.revature.models.Customer;
import com.revature.models.Users;

public class UsersDOTest {
	
	private UsersDAO userDao;
		
	@Before
	public void UserDAOImplementation() {
		userDao = new UserPostgresDAO();
	}
	
	
	@Test
	public void logIn() throws UserNotFound, InternalException, SQLException {
		
		Users user = userDao.findOne("admin@gmail.com", "123", true);
		Users userTest = new Customer("laith2@gmail.com", "123","Laith","Al");
		assertEquals(userTest.getEmail(), user.getEmail());
		assertEquals(userTest.getFirstName(),user.getFirstName());
		assertEquals(userTest.getLastName(),user.getLastName());
		
	}
	
	

}
