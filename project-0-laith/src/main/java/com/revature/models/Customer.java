package com.revature.models;

public class Customer extends Users {
			  	 
		private CustomerStatus customerStatus;
	  
		public Customer(String email, String password, String firstName, String lastName) {			
			super(email, password, firstName, lastName);
			super.setCustomer(true);
			
		}
		
		public Customer() {
			super.setCustomer(true);
		}
		
		public Customer(String customerStatus) {
			super.setCustomer(true);
			for(CustomerStatus cs : CustomerStatus.values()) {
				if(cs.toString().equals(customerStatus)) {
					this.customerStatus = cs;
				}
			}
		}


		public String getCustomerStatus() {
			return customerStatus.toString();
		}

		public void setCustomerStatus(String customerStatus) {
			
			for(CustomerStatus cs : CustomerStatus.values()) {
				if(cs.toString().equals(customerStatus)) {
					this.customerStatus = cs;
				}
			}
			
			
		}

		  	  
}
