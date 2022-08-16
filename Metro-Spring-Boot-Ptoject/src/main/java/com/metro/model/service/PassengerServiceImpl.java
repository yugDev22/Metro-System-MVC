package com.metro.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.bean.Passenger;
import com.metro.model.persistence.PassengerDao;
import com.metro.model.persistence.PassengerDaoImpl;

@Service
public class PassengerServiceImpl implements PassengerService {
	
	@Autowired
	private PassengerDao passengerDao;

	@Override
	public Passenger searchPassengerById(Integer id) {
		return passengerDao.searchPassenger(id);
	}

	@Override
	public Passenger addNewPassenger(Passenger passenger) {
		if(passengerDao.searchPassenger(passenger.getPassengerId())==null) {
			if(passengerDao.addPassenger(passenger)>0) {
				return passenger;
			}
		}
		return null;
	}

	@Override
	public Passenger updatingPassengerDetails(Passenger passenger) {
		if(passengerDao.searchPassenger(passenger.getPassengerId())!=null) {
			if(passengerDao.updatePassengerDetails(passenger)>0) {
				return passenger;
			}
		}
		return null;
	}

	@Override
	public Integer getPassengerId() {
		Passenger passenger = passengerDao.getLastPassenger();
		if(passenger!=null) {
			return passenger.getPassengerId()+1;
		}
		return 2001;
	}

}
