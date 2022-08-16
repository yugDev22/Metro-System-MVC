package com.metro.model.service;

import com.metro.bean.Passenger;

public interface PassengerService {

	public Passenger searchPassengerById(Integer id);
	public Passenger addNewPassenger(Passenger passenger);
	public Passenger updatingPassengerDetails(Passenger passenger);
	public Integer getPassengerId();
}
