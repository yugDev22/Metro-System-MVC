package com.metro.model.persistence.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.metro.bean.MetroUser;

public class MetroUserRowMapper implements RowMapper<MetroUser>{

	@Override
	public MetroUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		String uid = rs.getString("userId");
		String pwd = rs.getString("password");
		MetroUser metroUser = new MetroUser(uid, pwd);
		return metroUser;
	}

}
