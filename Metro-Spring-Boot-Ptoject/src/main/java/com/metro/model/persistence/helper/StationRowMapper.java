package com.metro.model.persistence.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.metro.bean.Station;

public class StationRowMapper implements RowMapper<Station> {

	@Override
	public Station mapRow(ResultSet rs, int rowNum) throws SQLException {
		Integer stationId = rs.getInt("stationId");
		String stationName = rs.getString("stationName");
		Integer prevStationId = rs.getInt("previousStationId");;
		Integer nextStationId = rs.getInt("nextStationId");;
		Station station = new Station(stationId, stationName, prevStationId, nextStationId);
		return station;
	}

}
