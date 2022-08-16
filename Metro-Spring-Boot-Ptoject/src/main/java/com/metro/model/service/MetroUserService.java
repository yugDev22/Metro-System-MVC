package com.metro.model.service;

import org.springframework.stereotype.Service;

import com.metro.bean.MetroUser;
import com.metro.bean.Passenger;


@Service
public interface MetroUserService {

	public boolean validateUser(String userID, String password);
	public Passenger getPassenger(String userId);
	public boolean registerUser(MetroUser user, Passenger passenger);

}
