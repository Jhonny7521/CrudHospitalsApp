package com.tismart.Reto1.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tismart.Reto1.models.District;
import com.tismart.Reto1.repositories.DistrictRepository;
import com.tismart.Reto1.services.IDistrictService;

@Service
public class DistrictServiceImpl implements IDistrictService{
	
	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public List<District> findAllDistricts() {
		return districtRepository.findAll();
	}

	@Override
	public void saveDistrict(District district) {
		districtRepository.save(district);		
	}

	@Override
	public District findDistrictById(int idDistrict) {
		Optional<District> optional = districtRepository.findById(idDistrict);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		return null;
	}

	@Override
	public void deleteDistrictById(int idDistrict) {
		districtRepository.deleteById(idDistrict);		
	}

}
