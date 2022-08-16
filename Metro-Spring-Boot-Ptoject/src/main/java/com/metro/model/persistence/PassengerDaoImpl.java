package com.metro.model.persistence;

import com.metro.bean.Passenger;
import com.metro.model.persistence.helper.PassengerRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerDaoImpl implements PassengerDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Passenger searchPassenger(Integer id) {
		Passenger passenger=null;
		try {
			String query = "SELECT * FROM PASSENGER WHERE PASSENGERID = ?";
			passenger=jdbcTemplate.queryForObject(query, new PassengerRowMapper(),id);
			
		} catch (EmptyResultDataAccessException ex) {
			return passenger;
		}
		return passenger;
	}

	@Override
	public int addPassenger(Passenger passenger) {
		int rows = 0;
		try {
			String query = "INSERT INTO PASSENGER values(?,?,?,?,?)";
			rows = jdbcTemplate.update(query,getLastPassenger().getPassengerId(),passenger.getPassengerName(),passenger.getPassengerPhoneNumber(),passenger.getPassengerEmail(),passenger.getAge());

		} catch (EmptyResultDataAccessException e) {
			return rows;
		}

		return rows;
	}

	@Override
	public int updatePassengerDetails(Passenger passenger) {
		int rows = 0;
		try {
			String query = "UPDATE PASSENGER SET PASSENGERNAME=?,PASSENGERPHONE=?,PASSENGEREMAIL=?,PASSENGERAGE=? WHERE PASSENGERID=?";
			rows = jdbcTemplate.update(query,passenger.getPassengerName(),passenger.getPassengerPhoneNumber(),passenger.getPassengerEmail(),passenger.getAge(),passenger.getPassengerId());

		} catch (DataAccessException e) {
			return rows;
		}

		return rows;

	}

	@Override
	public Passenger getLastPassenger() {
		Passenger passenger=null;
		try {
			String query = "SELECT * FROM passenger ORDER BY passengerid DESC LIMIT 1";
			passenger=jdbcTemplate.queryForObject(query, new PassengerRowMapper());
			
		} catch (EmptyResultDataAccessException ex) {
			return passenger;
		}
		return passenger;

	}

	@Override
	public Passenger searchPassengerByEmail(String email) {
		Passenger passenger=null;
		try {
			String query = "SELECT * FROM PASSENGER WHERE PASSENGEREMAIL = ?";
			passenger=jdbcTemplate.queryForObject(query, new PassengerRowMapper(), email);
			
		} catch (EmptyResultDataAccessException ex) {
			return passenger;
		}
		return passenger;
	}



}
