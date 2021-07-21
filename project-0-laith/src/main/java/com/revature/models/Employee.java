package com.revature.models;

public class Employee extends Users {

	public Employee(String email,String password, String firstName, String lastName) {
		super(email, password, firstName, lastName);
		super.setCustomer(false);
	}
	
	public Employee() {
		super.setCustomer(false);
	}

}
