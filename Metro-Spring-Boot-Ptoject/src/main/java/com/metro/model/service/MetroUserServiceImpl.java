package com.metro.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.bean.MetroUser;
import com.metro.bean.Passenger;
import com.metro.model.persistence.MetroUserDao;
import com.metro.model.persistence.PassengerDao;

@Service
public class MetroUserServiceImpl implements MetroUserService {

	@Autowired
	private MetroUserDao metroUserDao;
	
	@Autowired
	private PassengerDao passengerDao;
	
	@Override
	public boolean validateUser(String userID, String password) {
		MetroUser user = metroUserDao.getUser(userID);
		if(user!=null) {
			if(user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Passenger getPassenger(String userId) {
		Passenger passenger = passengerDao.searchPassengerByEmail(userId);
		if(passenger!=null) {
			return passenger;
		}
		return null;
	}

	@Override
	public boolean registerUser(MetroUser user, Passenger passenger) {
		if(metroUserDao.addUser(user)>0 && passengerDao.addPassenger(passenger)>0) {
			return true;
		}
		return false;
	}

	
}
