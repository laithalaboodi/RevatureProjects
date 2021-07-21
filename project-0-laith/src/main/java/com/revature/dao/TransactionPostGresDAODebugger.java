package com.revature.dao;

import com.revature.exception.InternalException;
import com.revature.models.BankAccount;
import com.revature.models.CheckingAccount;
import com.revature.models.Transactions;

public class TransactionPostGresDAODebugger {

	public static void main(String[] args) throws InternalException {
		// TODO Auto-generated method stub
		TransactionPosgresDAO tpd = new TransactionPosgresDAO();
		BankAccount ba = new CheckingAccount();
		Transactions tsc = new Transactions();

		System.out.println(tpd.findRepicient(4));
	}

}
