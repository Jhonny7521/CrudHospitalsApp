package com.tismart.Reto1.services;

import java.util.List;

import com.tismart.Reto1.models.Province;

public interface IProvinceService {
	
	List<Province> findAllProvinces();
	void saveProvince(Province province);
	Province findProvinceById(int idProvince);
	void deleteProvinceById(int idProvince);
	//Page<Province> findAllProvince(Pageable page);
}
