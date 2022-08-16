package com.metro.model.persistence.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;
import com.metro.bean.Transaction;

public class TransactionRowMapper implements RowMapper<Transaction>{

	@Override
	public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		String transactionId = rs.getString("transactionId");
		Integer cardId = rs.getInt("cardId");
		Integer boardingStationId = rs.getInt("boardingStationId");;
		Integer destinationStationId = rs.getInt("destinationStationId");;
		Double fare = rs.getDouble("fare");;
		LocalDateTime swipeInTime = (LocalDateTime) rs.getObject("swipeInDatetime");
		LocalDateTime swipeOutTime = (LocalDateTime) rs.getObject("swipeOutDatetime");;
		Transaction transaction = new Transaction(transactionId, cardId, boardingStationId, destinationStationId, fare, swipeInTime, swipeOutTime);
		return transaction;
	}

}
