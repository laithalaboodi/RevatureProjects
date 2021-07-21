package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.models.BankAccount;
import com.revature.models.CheckingAccount;
import com.revature.models.SavingAccount;
import com.revature.models.Users;
import com.revature.utilities.ConnectionUtililty;

public class PostgresDAO implements AccountDAO {
	
	private ConnectionUtililty cf = ConnectionUtililty.getConnectionFactory();
	
	
	public BankAccount saveOne(BankAccount newAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	public BankAccount findOne(Users userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateBalance(int bankId,BankAccount existingAccount) {
		Connection conn = cf.getConnection();
		String ca_account_number = null,sa_account_number=null;
		String sql =null;
		double newBalance = 0;

		
		try {
			if(existingAccount instanceof CheckingAccount) {
				sql = "update checking_account set ca_balance = ? "
						+ "where ca_account_number = ? and bank_id = ?;";
				ca_account_number = ((CheckingAccount) existingAccount).getAccountNumber();
				newBalance = ((CheckingAccount) existingAccount).getBalance();
			} else if (existingAccount instanceof SavingAccount){
				sql = "update saving_account set sa_balance = ? "
						+ "where sa_account_number = ? "
						+ "and bank_id = ?;";
				sa_account_number = ((SavingAccount) existingAccount).getAccountNumber();
				newBalance = ((SavingAccount) existingAccount).getBalance();
			}
			if(ca_account_number != null ) {
				PreparedStatement updateBalance = conn.prepareStatement(sql);
				updateBalance.setDouble(1, newBalance);
				updateBalance.setString(2, ca_account_number);
				updateBalance.setInt(3, bankId);
								
				updateBalance.execute();
				return true;
			} else if(sa_account_number != null) {
				PreparedStatement updateBalance = conn.prepareStatement(sql);
				updateBalance.setDouble(1, newBalance);
				updateBalance.setString(2, sa_account_number);
				updateBalance.setInt(3, bankId);
								
				updateBalance.execute();
				return true;
			}

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			 try {
					cf.releaseConnection(conn);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		}
		
		return false;
	}

	public void updateAccount(BankAccount account) {
		// TODO Auto-generated method stub

	}

}
