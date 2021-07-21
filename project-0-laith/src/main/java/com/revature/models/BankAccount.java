package com.revature.models;

public class BankAccount {
		
	    private int customerID;
	    private int bankID;
	    private boolean pendingTransaction;
	    private BankStatus bankingStatus;
	    
	 	    		
		public int getBankId() {
			return bankID;
		}
		public void setBankId(int bankId) {
			this.bankID = bankId;
		}
		public int getCustomerId() {
			return customerID;
		}
		public void setCustomerId(int customerId) {
			this.customerID = customerId;
		}


		public boolean isPendingTransaction() {
			return pendingTransaction;
		}
		public void setPendingTransaction(boolean pendingTransaction) {
			this.pendingTransaction = pendingTransaction;
		}
		public String getBankingStatus() {
			return bankingStatus.toString();
		}
		public void setBankingStatus(String bankingStatus) {
			for(BankStatus bankS : BankStatus.values()) {
				if(bankS.toString().equals(bankingStatus)) {
					this.bankingStatus = bankS; 
				}
			}
			
		}
		@Override
		public String toString() {
			return "BankingAccount Pending Transaction = " + pendingTransaction + ", Banking Status = " + bankingStatus
					+ "\n";
		}
		
		
	  
	  
}
