package com.tismart.Reto1.services;

import java.util.List;

import com.tismart.Reto1.models.District;

public interface IDistrictService {

	List<District> findAllDistricts();
	void saveDistrict(District district);
	District findDistrictById(int idDistrict);
	void deleteDistrictById(int idDistrict);
	//Page<District> findAllDistricts(Pageable page);
	
}
