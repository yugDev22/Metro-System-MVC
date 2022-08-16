package com.metro.model.persistence.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.metro.bean.MetroCard;

public class MetroCardRowMapper implements RowMapper<MetroCard>{

	@Override
	public MetroCard mapRow(ResultSet rs, int rowNum) throws SQLException {
		Integer cardId = rs.getInt("cardId");
		Integer passengerId = rs.getInt("passengerId");;
		Double balance = rs.getDouble("balance");;
		MetroCard metroCard = new MetroCard(cardId, passengerId, balance);
		return metroCard;
	}

}
