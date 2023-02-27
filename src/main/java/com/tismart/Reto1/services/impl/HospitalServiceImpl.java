package com.tismart.Reto1.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.tismart.Reto1.models.Hospital;
import com.tismart.Reto1.repositories.HospitalRepository;
import com.tismart.Reto1.services.IHospitalService;

@Service
public class HospitalServiceImpl implements IHospitalService{
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Override
	public void saveHospital(Hospital hospital) {
		hospitalRepository.save(hospital);		
	}

	@Override
	public List<Hospital> findAllHospitals() {
		return hospitalRepository.findAll();
	}

	@Override
	public Hospital finHospitalById(Integer idHospital) {
		Optional<Hospital> hospital = hospitalRepository.findById(idHospital);
		if(hospital.isPresent()) {
			return hospital.get();
		}
		return null;
	}

	@Override
	public long count() {		
		return hospitalRepository.count();
	}

	@Override
	public List<Hospital> findByExample(Example<Hospital> example) {
		return hospitalRepository.findAll(example);
	}

	@Override
	public long findAndCountByExample(Example<Hospital> example) {
		return hospitalRepository.count(example);
	}

	@Override
	public void deleteHospitalById(int idHospital) {
		hospitalRepository.deleteById(idHospital);		
	}

}
