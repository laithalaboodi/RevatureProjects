package com.revature.dao;

import java.util.List;

import com.revature.models.Transactions;

public interface TransactionsDAO {
	public Transactions saveOne(Transactions transaction);	
	
	public boolean updateOne(Transactions transaction);
	
	public List<Transactions> findRepicient(int userId);
}
