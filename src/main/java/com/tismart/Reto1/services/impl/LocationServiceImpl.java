package com.tismart.Reto1.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tismart.Reto1.models.Location;
import com.tismart.Reto1.repositories.LocationRepository;
import com.tismart.Reto1.services.ILocationService;

@Service
public class LocationServiceImpl implements ILocationService{

	@Autowired
	public LocationRepository locationRepository;
	
	@Override
	public List<Location> findAllLocations() {
		return locationRepository.findAll();
	}

	@Override
	public void saveLocation(Location location) {
		locationRepository.save(location);		
	}

	@Override
	public Location findLocationById(int idLocation) {
		Optional<Location> optional = locationRepository.findById(idLocation);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void deleteLocationById(int idLocation) {
		locationRepository.deleteById(idLocation);
		
	}

	@Override
	public List<Location> findLocationsWithoutHospital() {
		return locationRepository.findLocationsWithoutHospital();
	}

}
