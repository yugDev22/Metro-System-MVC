package com.metro.model.persistence.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.metro.bean.Passenger;

public class PassengerRowMapper implements RowMapper<Passenger>{

	@Override
	public Passenger mapRow(ResultSet rs, int rowNum) throws SQLException {
		Integer passengerId = rs.getInt("passengerid");
		String passengerName = rs.getString("passengername");
		String passengerPhoneNumber = rs.getString("passengerphone");;
		String passengerEmail = rs.getString("passengeremail");;
		Integer age = rs.getInt("passengerage");
		Passenger passenger = new Passenger(passengerId, passengerName, passengerPhoneNumber, passengerEmail, age);
		return passenger;
	}

	
}
