package com.metro.model.persistence;

import com.metro.bean.Passenger;

public interface PassengerDao {

	public Passenger searchPassenger(Integer id);
	public int addPassenger(Passenger passenger);
	public int updatePassengerDetails(Passenger passenger);
	public Passenger getLastPassenger();
	public Passenger searchPassengerByEmail(String email);
}
