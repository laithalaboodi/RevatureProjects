package com.revature.models;

public class BankAccount {
		
	    private int customerId;
	    private int bankId;
	    private String mailing_address;
	    private boolean pendingTransaction;
	    private BankingStatus bankingStatus;
	    
	    
	    		
		public int getBankId() {
			return bankId;
		}
		public void setBankId(int bankId) {
			this.bankId = bankId;
		}
		public int getCustomerId() {
			return customerId;
		}
		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}

	    public String getMailing_address() {
			return mailing_address;
		}
		public void setMailing_address(String mailing_address) {
			this.mailing_address = mailing_address;
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
			for(BankingStatus bs : BankingStatus.values()) {
				if(bs.toString().equals(bankingStatus)) {
					this.bankingStatus = bs; 
				}
			}
			
		}
		@Override
		public String toString() {
			return "BankingAccount Pending Transaction = " + pendingTransaction + ", Banking Status = " + bankingStatus
					+ "\n";
		}
		
		
	  
	  
}
