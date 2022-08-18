package com.metro.model.persistence;

import java.util.ArrayList;
import java.util.List;

import com.metro.bean.Transaction;

public interface TransactionDao {
	
	public List<Transaction> getTransactionsByCardId(int cardId);
	public int addTransaction(Transaction transaction);
	public Transaction alreadySwipedIn(Integer cardId);
	public Transaction getLastTransaction();
	public int updateTransaction(Transaction transaction);
}
