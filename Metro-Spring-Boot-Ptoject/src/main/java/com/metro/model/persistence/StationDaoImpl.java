package com.metro.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.metro.bean.Station;
import com.metro.model.persistence.helper.StationRowMapper;

@Repository
public class StationDaoImpl implements StationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Station searchStationById(int stationId) {
		Station station = null;
		String query = "SELECT * FROM stations where stationId=?";
		try {
			station = jdbcTemplate.queryForObject(query, new StationRowMapper(), stationId);
		} catch (EmptyResultDataAccessException e) {
			return station;
		}
		return station;
	}

	@Override
	public int addStation(Station station) {
		int rows = 0;
		try {
			String query = "INSERT INTO stations values(?,?,?,?)";
			rows = jdbcTemplate.update(query, station.getStationId(), station.getStationName(), station.getPrevStationId(),
					station.getNextStationId());
		} catch(DataAccessException e) {
			return rows;
		}
		return rows;
	}

	@Override
	public ArrayList<Station> getAllStations() 
	{
		List<Station> stationList = new ArrayList<Station>();
		try {
			String query = "SELECT * FROM stations";
			stationList = jdbcTemplate.query(query, new StationRowMapper());
		} catch(EmptyResultDataAccessException e) {
			return (ArrayList<Station>) stationList;
		}
		return (ArrayList<Station>) stationList;
	}

}
