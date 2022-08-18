package com.metro.model.service;

import java.util.ArrayList;
import java.util.List;

import com.metro.bean.Transaction;

public interface TransactionService {

	public List<Transaction> getAllTransactionsByCardId(int cardId);
	public int addNewTransaction(Transaction transaction);
	public Transaction alreadySwipedIn(Integer cardId);
	public Transaction updateTransaction(Transaction transaction);
}
