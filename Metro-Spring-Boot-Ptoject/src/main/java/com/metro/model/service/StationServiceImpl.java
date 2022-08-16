package com.metro.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.bean.Station;
import com.metro.model.persistence.StationDao;
import com.metro.model.persistence.StationDaoImpl;

@Service
public class StationServiceImpl implements StationService {
	@Autowired
	private StationDao stationDao;

	@Override
	public Station searchMetroStationById(int stationId) {
		return stationDao.searchStationById(stationId);
	}

	@Override
	public int addMetroStation(Station station) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Station> getAllStations() {
		return stationDao.getAllStations();
	}

}
