package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.application.Driver;
import com.revature.dao.AccountDAO;
import com.revature.dao.TransactionPosgresDAO;
import com.revature.dao.UserPostgresDAO;
import com.revature.exception.InternalException;
import com.revature.exception.UserNotFound;
import com.revature.models.BankAccount;
import com.revature.models.CheckingAccount;
import com.revature.models.SavingAccount;
import com.revature.models.Transactions;
import com.revature.models.Users;

public class CustomerServiceImplementation implements CustService,UserService {
    
    
    private UserPostgresDAO userPostgreDao;
    private AccountDAO bankAccountDao;
    private TransactionPosgresDAO transactionPosDao;
	    
	public CustomerServiceImplementation(UserPostgresDAO userPostgreDao,AccountDAO bankAccountDao,TransactionPosgresDAO transactionPosDao) {
		super();
		this.userPostgreDao = userPostgreDao;
		this.bankAccountDao = bankAccountDao;
		this.transactionPosDao = transactionPosDao;
	}

	public List<Object> viewCustomerInfo(Users customer) {
		List<Object> listInfo;
		try {
			listInfo = userPostgreDao.findCustomerInfoByEmail(customer);	
			if(listInfo.size() != 0) {
				return listInfo;
			}
		} catch (InternalException e) {
			e.printStackTrace();
			System.out.println(e);
		} 

		return null;		
	}


	public void applyNewAccountWithBalance(Users customer, double balance) {
		  Users user = userPostgreDao.createCustomerAccount(customer, balance);
		  if(user!= null) {
			   System.out.println("Welcome, "+ user.getFirstName()+" "+user.getLastName());
			   System.out.println("Thank you for registering new account! Your account is being reviewed!");
			}
			else {
				System.out.println("Creation is unsuccessful!");
			}
		  

	}
	
	
	public boolean deposit(int bankId,BankAccount existingAccount,double amount) {
		if(existingAccount instanceof CheckingAccount) {
			((CheckingAccount)existingAccount).setBalance(amount);
		} else if (existingAccount instanceof SavingAccount){
			((SavingAccount)existingAccount).setBalance(amount);
		}
		Driver.laithLogger.info("Banking Account with Id: "+bankId+" just got deposit: "+amount+"$!");
		return bankAccountDao.updateBalance(bankId ,existingAccount);
		
	}


	public boolean withdraw(int bankId,BankAccount existingAccount,double amount) {
		if(existingAccount instanceof CheckingAccount) {
			((CheckingAccount)existingAccount).withDraw(amount);
		} else if (existingAccount instanceof SavingAccount){
			((SavingAccount)existingAccount).withDraw(amount);
		}
		return bankAccountDao.updateBalance(bankId ,existingAccount);
	}
	
	
	public boolean transferMoney(String email, int userId,BankAccount existingAccount,double amount) {
		Transactions t = new Transactions();
		t.setRepicientEmail(email);
		
		if(existingAccount instanceof CheckingAccount) {
			t.setSenderAccountNumber(((CheckingAccount)existingAccount).getAccountNumber());
		} else if (existingAccount instanceof SavingAccount){
			t.setSenderAccountNumber(((SavingAccount)existingAccount).getAccountNumber());			
		}
		t.setSenderId(userId);
		t.setTransactionAmount(amount);
		Transactions newTransaction = transactionPosDao.saveOne(t);
		Driver.laithLogger.info("User with Id: "+userId+"just send "+amount+"$!");
		return newTransaction != null ? true : false;
	}

	public boolean acceptMoneyTransfer(Transactions transaction) {
		
		return transactionPosDao.updateOne(transaction);
	}
	
	public List<Transactions> findRepicient(int repicientId) {
		
		return transactionPosDao.findRepicient(repicientId);

	}
    
	
	public Users userLogIn(String email, String password, boolean isCustomer) throws UserNotFound, InternalException, SQLException {
		Users user = userPostgreDao.findOne(email, password, isCustomer);	
		Driver.laithLogger.info(user.getFirstName()+" "+user.getLastName()+" logged in!");
		if(user!= null) {
		   
		   return user;
		}

		return null;
	}
   
}
