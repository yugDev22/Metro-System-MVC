package com.metro.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.bean.MetroCard;
import com.metro.bean.Transaction;
import com.metro.model.persistence.TransactionDao;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private MetroCardService metroCardService;
	
	@Override
	public List<Transaction> getAllTransactionsByCardId(int cardId) {
		if(metroCardService.searchMetroCardById(cardId)!=null) {
			return transactionDao.getTransactionsByCardId(cardId);
		}
		return new ArrayList<Transaction>();
	}

	@Override
	public int addNewTransaction(Transaction transaction) {
		Transaction lastTran = transactionDao.getLastTransaction();
		String tid = "t0";
		if(lastTran!=null) {
			String lastId=lastTran.getTransactionId().split("t")[1];
			tid = "t"+Integer.toString(Integer.parseInt(lastId)+1);
		}
		transaction.setTransactionId(tid);
		if(transaction.getDestinationStationId()==null) {
			transaction.setDestinationStationId(0);
		}
		
		return transactionDao.addTransaction(transaction);
	}

	@Override
	public Transaction alreadySwipedIn(Integer cardId) {
		if(metroCardService.searchMetroCardById(cardId)!=null) {
			return transactionDao.alreadySwipedIn(cardId);
		}
		return null;
	}

	@Override
	public Transaction updateTransaction(Transaction transaction) {
		MetroCard card = metroCardService.searchMetroCardById(transaction.getCardId());
		int sourceStation = transaction.getBoardingStationId();
		int destStation = transaction.getDestinationStationId();
		double fare = Math.abs(destStation-sourceStation)*5;
		transaction.setFare(fare);
		if(fare>card.getBalance()){
			return null;
		}
		metroCardService.deductBalance(card.getCardId(), fare);
		if(transactionDao.updateTransaction(transaction)>0) {
			return transaction;
		}
		else return null;
	}
	

}
