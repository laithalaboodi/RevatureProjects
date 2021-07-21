package com.revature.displays;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.exception.InternalException;
import com.revature.exception.UserNotFound;
import com.revature.models.BankAccount;
import com.revature.models.CheckingAccount;
import com.revature.models.Customer;
import com.revature.models.SavingAccount;
import com.revature.models.Transactions;
import com.revature.models.Users;
import com.revature.services.CustomerServiceImplementation;
import com.revature.services.EmployeeServiceImplementation;

public class Menu implements DisplayInterface {
		
	  private CustomerServiceImplementation customerServImp;
	  private EmployeeServiceImplementation employeeServImp;
	  Scanner userInput;
	 
	  
	  public Menu(CustomerServiceImplementation customerServImp, EmployeeServiceImplementation employeeServImp) {
		  this.customerServImp = customerServImp;
		  this.employeeServImp = employeeServImp;
		  userInput = new Scanner(System.in);
	  }
	  

	public String display() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void manageUserAccountInput() throws UserNotFound, InternalException, SQLException,InputMismatchException {
 
		String email,password;	
		int accountType;
		System.out.println("*******************\n");
		System.out.println("Your banking options:");
	    System.out.println("1. New Customer");
		System.out.println("2. Customer");
		System.out.println("3. Employee \n");
		System.out.println("*******************\n");
		
		System.out.println("Your pick: ");
	    try {
	    	
	    	accountType = this.userInput.nextInt();
	    	if(accountType < 0 || accountType > 3) {
	    		System.out.println("Please select the account 1, 2, or 3");
	    	}
	    	
	    	else {
	    		if(accountType == 1) {
	    			this.userInput.nextLine();
	    			manageNewCustomer();
	    		} if(accountType == 2) {
	    			this.userInput.nextLine();
	    			System.out.println("Welcome Back!\n ");
	    			System.out.println("Please enter your email: ");
	    			email = this.userInput.nextLine();
	    			System.out.println("Please enter your password: ");
	    			password = this.userInput.nextLine();
	    			Users customer = customerServImp.userLogIn(email,password, true);
	    			if(customer.isCustomer()) {
	    				//Enter customer menu
	    				//this.userIn.nextLine();
	    				manageCustomerAccount(customer);
	    			};
	    		}
	    		else if(accountType == 3){
	    			System.out.println("Welcome Back!\n ");
	    			this.userInput.nextLine();
	    			System.out.println("Please enter your employee email: ");
	    			email = this.userInput.nextLine();
	    			System.out.println("Please enter your password: ");
	    			password = this.userInput.nextLine();
	    			Users employee = employeeServImp.userLogIn(email,password, false);
	    			if(!employee.isCustomer()) {
	    				 
	    				manageEmployeeAccount(employee);
	    			}
	    		}
	    			    			    		
	    	}
	    	
	    } catch(InputMismatchException e) {
	    	 System.out.println("Please select the options based on number!\n");
	    } catch(UserNotFound u) {
	    	System.out.println("Wrong user or password! \n");
	    }
	}
    
	public void manageNewCustomer() {
	
		while(true) {
			String email,password,firstName,lastName;	
			System.out.println("Choose the best options for you: \n");
			System.out.println("1. Register new account without deposit\n"
					+ "2. Register new account with deposit \n"
					+ "3. Return to main menu");
			System.out.println("Your pick: ");
			int option = this.userInput.nextInt();
			if(option ==3)
				break;
			Users customer = new Customer();
			if(option == 1 || option  == 2) {
				this.userInput.nextLine();
				System.out.println("Please enter your email: ");
				email = this.userInput.nextLine();
				customer.setEmail(email);
				System.out.println("Please enter your password: ");
				password = this.userInput.nextLine();
				customer.setPassword(password);
				System.out.println("Please enter your first name: ");
				firstName = this.userInput.nextLine();
				customer.setFirstName(firstName);
				System.out.println("Please enter your last name: ");
				lastName = this.userInput.nextLine();
				customer.setLastName(lastName);
				
			} 
			
			if(option == 1) {
				
				this.customerServImp.applyNewAccountWithBalance(customer, 0);

			}
			else if (option == 2) {
				System.out.println("Please enter your first deposit: ");
				double balance = this.userInput.nextDouble();
				this.customerServImp.applyNewAccountWithBalance(customer, balance);
			} else {
				this.userInput.nextLine();
				System.out.println("Please enter option 1 or 2! \n");
				System.out.println("Would you like to try again! \n "
						+ "Type 'y' to continue 'n' to exit this menu:\n ");
				String type = this.userInput.nextLine();
				if(type.equals("n")) {
					break;
				}

			}
		}
		
	}
	
	
	
	
	public void manageEmployeeAccount(Users employee) {
		   while(true) {
			   System.out.println("Please choose the option below!\n");
			   System.out.println("1. View pending customer\n"
			   		+ "2. View a customer's bank account\n"
			   		+ "3. View a log of all transactions\n"
			   		+ "4. Return to the main menu\n");
			  
				System.out.println("Your pick: ");
				int option = this.userInput.nextInt();
			   if(option == 4)
				   break;
			   switch(option) {
			   		case 1:
			   			viewPendingAccount();
			   			break;
			   		case 2:
			   			viewCustomerBankAccount();
			   			break;
			   		case 3:
			   			break;
			   		default: {
			   		 this.userInput.nextLine();
						System.out.println("Please enter option 1, 2, or 3 ! \n");
			   			break;
			   		}
			   			
			   
			   }
			   
				System.out.println("Would you like to come to the main menu! \n "
						+ "1. Yes\n 2. No\n ");
				int type = this.userInput.nextInt();
				if(type == 1) {
					break;
				}
		   }
		   
	}
	
	
	
	public void viewPendingAccount() {
		while(true) {
			 this.userInput.nextLine();
			 System.out.println("This is the list of pending customers:\n");
			 List<Users> list=null;
			 try {
				 list = employeeServImp.viewListPendingUser();
				 if(list != null) {
					 System.out.println(list); 
				 }
				 else {
					 System.out.println("There is no pending users!\n");
				 }
				
			} catch (InternalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if(list!=null) {
				 
				 System.out.println("Please enter the customer id!\n");
					int customerId = this.userInput.nextInt();
					System.out.println("Which option do you choose:\n"
							+ "1. Accept\n"
							+ "2. Reject\n");
					int option = this.userInput.nextInt();
					Users customer=null;
					if(option == 1) {
						
						for(Users u :list) {
							if(u.getUserId()==customerId)
								  customer = u;
						}
						if(employeeServImp.approveCustomer(customer))
						   System.out.println("Customer is accepted!");
						else {
							System.out.println("There is something wrong");
						}
					}
					else if(option ==2) {
						for(Users u :list) {
							if(u.getUserId()==customerId)
								  customer = u;
						}
						if(employeeServImp.rejectCustomer(customer)) {
							System.out.println("Customer is rejected!");
						} else {
							System.out.println("There is something wrong");
						}
					} else {
						System.out.println("Please enter 1 or 2!\n");
					}
			 } 
			
			
			 System.out.println("Would you like to try another customer! \n "
						+ "1. Yes\n 2. No\n ");
				int type = this.userInput.nextInt();
				if(type == 2) {
					break;
				}
			 
		}
	}
	
	
	public void viewCustomerBankAccount() {
		   this.userInput.nextLine();
		   System.out.println("Please enter the customer's email:\n");
		   String email = this.userInput.nextLine();
		   Users customer = new Customer();
		   customer.setEmail(email);
		   List<Object> list = employeeServImp.viewCustomerInfo(customer);
		   if(list.size()>0)
			   System.out.println(list); 
		   else {
			   System.out.println("Customer is not found!\n");
		   }
	}
	
	
	public void viewLogOfTransaction() {
		
	}
	
	
	public void manageCustomerAccount(Users user) {
		while(true)  {
			List<Object> list = customerServImp.viewCustomerInfo(user);
			if(list == null) {
				System.out.println("Welcome "+ user.getFirstName()+" "+user.getLastName()+"!\n"
						+ "Your account is under review! Please wait!\n");
				System.out.println("It may take 3 - 5 business days to complete the review. \n");
				
				break;
			}
			BankAccount bankingAccount = new BankAccount();
			CheckingAccount checkingAccount = new CheckingAccount();
			SavingAccount savingAccount = new SavingAccount();
			for(Object o: list) {
				 if(o instanceof CheckingAccount) {
					checkingAccount = (CheckingAccount) o;
				} else if(o instanceof SavingAccount) {
					savingAccount = (SavingAccount) o;
				} else if(o instanceof BankAccount) {
					bankingAccount = (BankAccount)o;
				} 
			}
			
			System.out.println("Welcome "+ user.getFirstName()+" "+user.getLastName()+"!\n");
			
			System.out.println("Select the options below for your account:\n");
			System.out.println("1. View your account\n"
					         + "2. Deposit money to your account\n"
					         + "3. Withdraw money from your account\n"
					         + "4. View pending transaction\n"
					         + "5. Send money to another account\n"
					         + "6. Return to main menu\n");
			System.out.println("Your pick: ");
			int option = this.userInput.nextInt();
			switch(option) {
			    case 1: 
			    	viewCustomerAccountDetail(checkingAccount,savingAccount);
			    	break;
			    case 2:
			    	depositMoney(bankingAccount,checkingAccount,savingAccount);
			    	break;
			    case 3: 
			    	withdrawMoney(bankingAccount,checkingAccount,savingAccount);
			    	break;
			    case 4:
			    	viewPendingTransaction(user.getUserId(),bankingAccount,checkingAccount,savingAccount);
			    	break;
			    case 5:
			    	sendMoney(user.getUserId(),bankingAccount,checkingAccount,savingAccount);
			    	break;
			    default:
			    	break;
			}
			
			if(option==6) {
				break;
			}
			
			
		} 			
			
		
	}
	
	
	public void viewCustomerAccountDetail(CheckingAccount checkingA,SavingAccount savingA) {
		  this.userInput.nextLine();  
		  System.out.println("Your accounts:\n");
		  System.out.println(checkingA);
		  System.out.println(savingA);

	}

	public void depositMoney (BankAccount bankA, CheckingAccount checkingA,SavingAccount savingA) {
		
		System.out.println("Which account do you need to deposit?\n"
				+ "1. Checking Account\n"
				+ "2. Saving Account\n");
		int option = this.userInput.nextInt();
		double amount;
		while(true) {
			System.out.println("Please enter your amount:\n");
			amount =this.userInput.nextDouble();
			if(amount>0)
				break;
			else {
				System.out.println("Please enter positive amount!\n");
			}
		}
		
		this.userInput.nextLine();
		if(option ==1) {
			if(customerServImp.deposit(bankA.getBankId(),checkingA, amount))
				System.out.println("You successfully deposited to your account!");		
		} else if(option == 2) {
			if(customerServImp.deposit(bankA.getBankId(), savingA, amount))
				System.out.println("You successfully deposited to your account!");
		} else {
			System.out.println("Please try again enter 1 or 2\n");
		}

	}
	
	public void withdrawMoney(BankAccount bankA, CheckingAccount checkingA,SavingAccount savingA) {
		System.out.println("Which account do you need to withdraw?\n"
				+ "1. Checking Account\n"
				+ "2. Saving Account\n");
		int option = this.userInput.nextInt();
		double amount;

		while(true) {
			System.out.println("Please enter your amount:\n");
			amount =this.userInput.nextDouble();
			if(option == 1 && (amount > checkingA.getBalance()) || amount <0) {
				System.out.println("Your amount cannot withdraw amount bigger than the Checking Account balance or less than 0!\n");
			} else if(option == 2 && (amount > savingA.getBalance() || amount < 0)){
				System.out.println("Your amount cannot withdraw amount bigger than the Saving Account balance or less than 0!\n");
			} else {
				break;
			}
			
		}
		
		this.userInput.nextLine();
		if(option ==1) {
			if(customerServImp.withdraw(bankA.getBankId(),checkingA, amount))
				System.out.println("You successfully withraw from your account!");		
		} else if(option == 2) {
			if(customerServImp.withdraw(bankA.getBankId(), savingA, amount))
				System.out.println("You successfully withraw from your account!");
		} else {
			System.out.println("Please try again enter 1 or 2\n");
		}

	}
	
	public void viewPendingTransaction(int repicientId,BankAccount bankA, CheckingAccount checkingA,SavingAccount savingA) {
		while(true) {
			System.out.println("These below are your pending transactions: \n");
			this.userInput.nextLine();
			List<Transactions> list = customerServImp.findRepicient(repicientId);
			if(list.size()>0) {
				System.out.println(list);
				System.out.println("Please enter the transaction ID to accept: ");
				int option = this.userInput.nextInt();
				System.out.println(option);
				for(Transactions trans : list) {
					if(trans.getTransactionId() == option) {
						System.out.println("Which account do you need to deposit?\n"
								+ "1. Checking Account\n"
								+ "2. Saving Account\n");
						int choice = this.userInput.nextInt();
						this.userInput.nextLine();
						if(choice ==1) {
							if(customerServImp.deposit(bankA.getBankId(),checkingA, trans.getTransactionAmount())) {
								customerServImp.acceptMoneyTransfer(trans);
								System.out.println("You successfully deposited to your account!");	
							}
									
						} else if(choice == 2) {
							if(customerServImp.deposit(bankA.getBankId(), savingA, trans.getTransactionAmount())) {
								customerServImp.acceptMoneyTransfer(trans);
								System.out.println("You successfully deposited to your account!");
							}
								
						} else {
							System.out.println("Please try again enter 1 or 2\n");
						}
										
					}
				}
			}
			else {
				System.out.println("You don't have any pending transaction!");
			}
			
			 System.out.println("Would you like to try another transaction! \n "
						+ "1. Yes\n 2. No\n ");
				int type = this.userInput.nextInt();
				if(type == 2) {
					break;
				}	
		}
		
		
	}
	
	public void sendMoney(int userId,BankAccount bankA, CheckingAccount checkingA, SavingAccount savingA) {
		this.userInput.nextLine();   
		System.out.println("Please enter the email of the repicient:\n");
		   String email = this.userInput.nextLine();
		   System.out.println("Which account do you need to withdraw for sending money?\n"
					+ "1. Checking Account\n"
					+ "2. Saving Account\n");
			int option = this.userInput.nextInt();
			System.out.println("Please enter your amount:\n");
			double amount =this.userInput.nextDouble();
			this.userInput.nextLine();
			if(option ==1) {
				if(customerServImp.withdraw(bankA.getBankId(),checkingA, amount)) {
					
					if(customerServImp.transferMoney(email, userId, checkingA, amount))
						System.out.println("You successfully transfered the money!");
				}
							
			} else if(option == 2) {
				if(customerServImp.withdraw(bankA.getBankId(), savingA, amount))
				{
					if(customerServImp.transferMoney(email, userId, savingA, amount))
						System.out.println("You successfully transfered the money!");
				}
			} else {
				System.out.println("Please try again enter 1 or 2\n");
			}

		   
	}
	
	
	  
	  
	  
	  
	  
	  
}
