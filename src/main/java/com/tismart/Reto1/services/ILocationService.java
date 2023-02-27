package com.tismart.Reto1.services;

import java.util.List;

import com.tismart.Reto1.models.Location;

public interface ILocationService {
	
	List<Location> findAllLocations();
	List<Location> findLocationsWithoutHospital();
	void saveLocation(Location location);
	Location findLocationById(int idLocation);
	void deleteLocationById(int idLocation);
	//Page<Manager> findAllLocations(Pageable page);
}	
