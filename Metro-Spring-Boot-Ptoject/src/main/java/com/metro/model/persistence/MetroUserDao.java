package com.metro.model.persistence;

import com.metro.bean.MetroUser;

public interface MetroUserDao {

	public int addUser(MetroUser metroUser);
	public MetroUser getUser(String userId);
}
