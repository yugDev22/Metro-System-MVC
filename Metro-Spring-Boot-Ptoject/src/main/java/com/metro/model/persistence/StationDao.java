package com.metro.model.persistence;

import java.util.ArrayList;
import java.util.List;

import com.metro.bean.Station;

public interface StationDao {
	
	public Station searchStationById(int stationId);
	public int addStation(Station station);
	public ArrayList<Station> getAllStations();
}
