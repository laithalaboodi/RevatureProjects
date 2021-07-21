package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.exception.InternalException;
import com.revature.exception.UserNotFound;
import com.revature.models.Users;

public interface UserService {
      public Users userLogIn(String email,String password, boolean isCustomer)
      throws UserNotFound,InternalException,SQLException;
      
      public List<Object> viewCustomerInfo(Users customer);
}
