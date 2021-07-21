package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exception.InternalException;
import com.revature.exception.UserNotFound;
import com.revature.models.BankAccount;
import com.revature.models.BankStatus;
import com.revature.models.CheckingAccount;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.SavingAccount;
import com.revature.models.Users;
import com.revature.utilities.ConnectionUtililty;

public class UserPostgresDAO implements UsersDAO {
	
	private ConnectionUtililty cf = ConnectionUtililty.getConnectionFactory();

	public Users createCustomerAccount(Users user, double balance) {
		// TODO Auto-generated method stub
		
		Connection conn = cf.getConnection();
		try {
			
			conn.setAutoCommit(false);
			String sql = "insert into \"user\" (\"email\",\"password\",\"first_name\",\"last_name\",\"isCustomer\",\"user_status\")\r\n"
					+ "values(?,?,?,?,true,'PENDING') returning \"user_id\";";
			
			PreparedStatement createCustomerAccount = conn.prepareStatement(sql);
			createCustomerAccount.setString(1, user.getEmail());
			createCustomerAccount.setString(2, user.getPassword());
			createCustomerAccount.setString(3, user.getFirstName());
			createCustomerAccount.setString(4, user.getLastName());
			
			
			
			ResultSet res = createCustomerAccount.executeQuery();
			
			int newCustomerId;
			if(res.next()) {
				newCustomerId = res.getInt("user_id");
				
			} else {
				throw new SQLException();
			}
			
			//do st with banking account
			String bankingSql = "insert into \"banking_account\" (customer_id,pending_transaction,mailing_address,banking_status,initial_deposit)\r\n"
					+ "values(?,false,'Not Available','PENDING',?);";
			
			PreparedStatement applyNewBanking = conn.prepareStatement(bankingSql);
			applyNewBanking.setInt(1, newCustomerId);
			applyNewBanking.setDouble(2, balance);
			
			applyNewBanking.execute();
			
			
		} catch (SQLException e ) {
			  e.printStackTrace();
			  try {
				  	conn.rollback();
			  } catch(SQLException e1) {
				  e1.printStackTrace();
			  }
		} finally {
			
			 try {
				 conn.commit();
				 conn.setAutoCommit(true);
			 } catch (SQLException e) {
				 e.printStackTrace();
			 }
			 
			 try {
				cf.releaseConnection(conn);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return user;
	}
	
	public boolean acceptOne(Users user,CheckingAccount ca,SavingAccount sa) {
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);
			String bankIdSql = "select ba.bank_id from \"user\" u join banking_account ba \r\n"
					+ "on u.user_id = ba.customer_id \r\n"
					+ "where user_id = ?;";
			PreparedStatement getBankId = conn.prepareStatement(bankIdSql);
			getBankId.setInt(1, user.getUserId());
			
			ResultSet res = getBankId.executeQuery();
			int bankId;
			if(res.next()) {
				bankId = res.getInt("bank_id");
				
			} else {
				throw new SQLException();
			}
			

			String userSql = "update \"user\" set user_status = 'ACTIVE' where user_id =?;";
			PreparedStatement updateUser = conn.prepareStatement(userSql);
			updateUser.setInt(1, user.getUserId());
			updateUser.executeUpdate();
			
			String bankAccountSql = "update \"banking_account\" set banking_status = 'ACTIVE' where bank_id =?;";
			PreparedStatement updateBankingAccount = conn.prepareStatement(bankAccountSql);
			updateBankingAccount.setInt(1, bankId);
			updateBankingAccount.executeUpdate();
		
			String insertCheckingAccountSql = "insert into checking_account (bank_id,ca_account_number,ca_balance) \r\n"
					+ "values(?,?,?);";
			PreparedStatement insertCheckingAccount = conn.prepareStatement(insertCheckingAccountSql);
			insertCheckingAccount.setInt(1, bankId);
			insertCheckingAccount.setString(2, ca.getAccountNumber());
			insertCheckingAccount.setDouble(3, ca.getBalance());
			insertCheckingAccount.execute();
		
			String insertSavingAccountSql = "insert into saving_account (bank_id,sa_account_number,sa_balance) \r\n"
					+ "values(?,?,?);";
			PreparedStatement insertSavingAccount = conn.prepareStatement(insertSavingAccountSql);
			insertSavingAccount.setInt(1, bankId);
			insertSavingAccount.setString(2, sa.getAccountNumber());
			insertSavingAccount.setDouble(3, sa.getBalance());
			insertSavingAccount.execute();
			return true;
			
		} catch(SQLException e) {
			 e.printStackTrace();
			  try {
				  	conn.rollback();
			  } catch(SQLException e1) {
				  e1.printStackTrace();
			  }
		} finally {
			try {
				 conn.commit();
				 conn.setAutoCommit(true);
			 } catch (SQLException e) {
				 e.printStackTrace();
			 }
			 
			 try {
				cf.releaseConnection(conn);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean rejectOne(Users user) {
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);
			String bankIdSql = "select ba.bank_id from \"user\" u join banking_account ba \r\n"
					+ "on u.user_id = ba.customer_id \r\n"
					+ "where user_id = ?;";
			PreparedStatement getBankId = conn.prepareStatement(bankIdSql);
			getBankId.setInt(1, user.getUserId());
			
			ResultSet res = getBankId.executeQuery();
			int bankId;
			if(res.next()) {
				bankId = res.getInt("bank_id");
				
			} else {
				throw new SQLException();
			}
			

			String userSql = "update \"user\" set user_status = 'CLOSED' where user_id =?;";
			PreparedStatement updateUser = conn.prepareStatement(userSql);
			updateUser.setInt(1, user.getUserId());
			updateUser.executeUpdate();
			
			String bankAccountSql = "update \"banking_account\" set banking_status = 'CLOSED' where bank_id =?;";
			PreparedStatement updateBankingAccount = conn.prepareStatement(bankAccountSql);
			updateBankingAccount.setInt(1, bankId);
			updateBankingAccount.executeUpdate();
			
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
			  try {
				  	conn.rollback();
			  } catch(SQLException e1) {
				  e1.printStackTrace();
			  }
		}finally {
			try {
				 conn.commit();
				 conn.setAutoCommit(true);
			 } catch (SQLException e) {
				 e.printStackTrace();
			 }
			 
			 try {
				cf.releaseConnection(conn);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return false;
	}
				
	public List<Users> findPendingCustomer()
			throws InternalException, SQLException {
		Connection conn = cf.getConnection();
		List<Users> listPendingUser = new ArrayList<Users>();
		
		try {
			String sql = "select u.user_id,u.email,u.first_name,u.last_name,u.user_status,\r\n"
					+ "ba.bank_id,ba.banking_status ,ba.initial_deposit\r\n"
					+ "from \"user\" u \r\n"
					+ "join banking_account ba \r\n"
					+ "on u.user_id  = ba.customer_id\r\n"
					+ "where u.\"user_status\" = 'PENDING' and u.\"isCustomer\" = true;";
			PreparedStatement getPendingUser = conn.prepareStatement(sql);
			ResultSet res = getPendingUser.executeQuery();
		
			while(res.next()) {
				Users u = new Customer();
				u.setUserId(res.getInt("user_id"));
				u.setFirstName(res.getString("first_name"));
				u.setLastName(res.getString("last_name"));
				u.setEmail(res.getString("email"));
				u.setInitialDeposit(res.getDouble("initial_deposit"));
				
				listPendingUser.add(u);
			} 
			
			
					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InternalException();
			
		} finally {
			cf.releaseConnection(conn);
		}
		
		return listPendingUser;
		
	}

	public Users findOne(String email, String password, boolean isCustomer) throws UserNotFound, InternalException, SQLException{
		
		Connection conn = cf.getConnection();
		
		try {
			String sql = "select * from \"user\" where email = ? and password = ?;";
			PreparedStatement getUser = conn.prepareStatement(sql);
			
			getUser.setString(1,email);
			getUser.setString(2,password);

			ResultSet res = getUser.executeQuery();
			
			if(res.next()) {
				Users u;
				if(isCustomer) {
					u = new Customer(res.getString("user_status"));
				} else {
					u = new Employee();
				}
				u.setUserId(res.getInt("user_id"));
				u.setFirstName(res.getString("first_name"));
				u.setLastName(res.getString("last_name"));
				u.setEmail(res.getString("email"));
				
				return u;
			}
			else {
				throw new UserNotFound();
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InternalException();
		} finally {
			cf.releaseConnection(conn);
			
		}
		
		
		
	}

	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<Object> findCustomerInfoByEmail(Users user) throws InternalException
	{
		Connection conn = cf.getConnection();
		List<Object> listCustomerInfo = new ArrayList<Object>();

		
		try {
			String sql = "select u.user_id,u.email, u.first_name ,u.last_name, u.user_status,\r\n"
					+ "ba.banking_status,ba.pending_transaction, ba.bank_id,ba.customer_id,\r\n"
					+ "ca.ca_account_number,ca.ca_balance,\r\n"
					+ "sa.sa_account_number,sa.sa_balance\r\n"
					+ "\r\n"
					+ "from \"user\" u \r\n"
					+ "inner join banking_account ba on u.user_id = ba.customer_id \r\n"
					+ "inner join checking_account ca on ba.bank_id = ca.bank_id \r\n"
					+ "inner join saving_account sa on ba.bank_id = sa.bank_id \r\n"
					+ "where u.\"email\" = ? \r\n"
					+ "and u.user_status = 'ACTIVE' \r\n"
					+ "and ba.banking_status = 'ACTIVE';";
		
			PreparedStatement getCustomerInfo = conn.prepareStatement(sql);
			
			getCustomerInfo.setString(1,user.getEmail());
			
			ResultSet res = getCustomerInfo.executeQuery();
			
			while(res.next()) {
				Users customer = new Customer();
				BankAccount bankingAccount = new BankAccount();
				CheckingAccount chequeingAccount =new CheckingAccount();
				SavingAccount savingAccount = new SavingAccount();
				customer.setUserId(res.getInt("user_id"));
				customer.setFirstName(res.getString("first_name"));
				customer.setLastName(res.getString("last_name"));
				customer.setEmail(res.getString("email"));
			    
				
				listCustomerInfo.add(customer);
				bankingAccount.setBankId(res.getInt("bank_id"));
				bankingAccount.setCustomerId(res.getInt("customer_id"));
				bankingAccount.setBankingStatus(res.getString("banking_status"));
				bankingAccount.setPendingTransaction(res.getBoolean("pending_transaction"));
				
				listCustomerInfo.add(bankingAccount);
				chequeingAccount.setAccountNumber(res.getString("ca_account_number"));
				chequeingAccount.setBalance(res.getDouble("ca_balance"));
				
				listCustomerInfo.add(chequeingAccount);
				savingAccount.setAccountNumber(res.getString("sa_account_number"));
				savingAccount.setBalance(res.getDouble("sa_balance"));
				listCustomerInfo.add(savingAccount);
				
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InternalException();
			
		} finally {
			try {
				cf.releaseConnection(conn);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		 return listCustomerInfo;
	}

	
}
