package com.metro.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.metro.bean.Transaction;
import com.metro.model.persistence.helper.TransactionRowMapper;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Transaction> getTransactionsByCardId(int cardId) {
		List<Transaction> transactionArray = new ArrayList<Transaction>();
		try {
			String query = "SELECT * FROM transaction WHERE cardID=?";
			transactionArray = jdbcTemplate.query(query, new TransactionRowMapper(), cardId);
		} catch (EmptyResultDataAccessException ex) {
			return transactionArray;
		}

		return transactionArray;
	}

	@Override
	public int addTransaction(Transaction transaction) {
		int rows = 0;
		try {
			String query = "INSERT INTO transaction VALUES(?,?,?,?,?,?,?)";
			rows = jdbcTemplate.update(query, transaction.getTransactionId(), transaction.getBoardingStationId(),
					transaction.getDestinationStationId(), transaction.getFare(), transaction.getCardId(),
					transaction.getSwipeInTime(), transaction.getSwipeOutTime());
		} catch (DataAccessException ex) {
			return rows;
		}
		return rows;
	}

	@Override
	public Transaction getLastTransaction() {
		Transaction transaction = null;
		try {
			String query = "SELECT * FROM transaction ORDER BY transactionId DESC LIMIT 1";
			transaction = jdbcTemplate.queryForObject(query, new TransactionRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return transaction;
		}
		return transaction;
	}

	@Override
	public Transaction alreadySwipedIn(Integer cardId) {
		Transaction swipedIn = null;
		try {
			String query = "SELECT * FROM transaction WHERE cardID=? AND destinationStationId=0";
			swipedIn = jdbcTemplate.queryForObject(query, new TransactionRowMapper(),cardId);
		}catch(EmptyResultDataAccessException e) {
			return swipedIn;
		}
		return swipedIn;
	}

	@Override
	public int updateTransaction(Transaction transaction) {
		int rows = 0;
		try {
			String query = "UPDATE transaction SET destinationStationId=?,fare=?,swipeOutDatetime=? WHERE transactionId=?";
			rows = jdbcTemplate.update(query,
			transaction.getDestinationStationId(),
			transaction.getFare(),
			transaction.getSwipeOutTime(),
			transaction.getTransactionId());
		} catch (DataAccessException e) {
			return rows;
		}
		return rows;
	}

}
