package com.tismart.Reto1.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tismart.Reto1.models.Province;
import com.tismart.Reto1.repositories.ProvinceRepository;
import com.tismart.Reto1.services.IProvinceService;

@Service
public class ProvinceServiceImpl implements IProvinceService{

	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Override
	public List<Province> findAllProvinces() {
		return provinceRepository.findAll();
	}

	@Override
	public void saveProvince(Province province) {
		provinceRepository.save(province);		
	}

	@Override
	public Province findProvinceById(int idProvince) {
		Optional<Province> optional = provinceRepository.findById(idProvince);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void deleteProvinceById(int idProvince) {
		provinceRepository.deleteById(idProvince);	
	}

}
