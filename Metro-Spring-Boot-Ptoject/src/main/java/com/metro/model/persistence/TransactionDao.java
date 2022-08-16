package com.metro.model.persistence;

import java.util.ArrayList;

import com.metro.bean.Transaction;

public interface TransactionDao {
	
	public ArrayList<Transaction> getTransactionsByCardId(int cardId);
	public int addTransaction(Transaction transaction);
	public Transaction alreadySwipedIn(Integer cardId);
	public Transaction getLastTransaction();
	public int updateTransaction(Transaction transaction);
}
