package com.metro.model.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.metro.bean.MetroUser;
import com.metro.model.persistence.helper.MetroUserRowMapper;

@Repository
public class MetroUserDaoImpl implements MetroUserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int addUser(MetroUser metroUser) {
		String query = "INSERT INTO metrouser VALUES(?,?)";
		int rows = jdbcTemplate.update(query,metroUser.getUserId(),metroUser.getPassword());
		return rows;
	}

	@Override
	public MetroUser getUser(String userId) {
		MetroUser metroUser = null;
		String query = "SELECT * FROM metrouser where userid=?";
		try {
			metroUser = jdbcTemplate.queryForObject(query, new MetroUserRowMapper() ,userId);
		} catch(EmptyResultDataAccessException ex) {
			return metroUser;
		}
		return metroUser;
	}

}
