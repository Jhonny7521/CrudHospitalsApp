package com.tismart.Reto1.services;

import java.util.List;

import org.springframework.data.domain.Example;

import com.tismart.Reto1.models.Hospital;

public interface IHospitalService {
	void saveHospital(Hospital hospital);
	List<Hospital> findAllHospitals();
	Hospital finHospitalById(Integer idHospital);
	void deleteHospitalById(int idHospital);
	long count();
	List<Hospital> findByExample(Example<Hospital> example);
	long findAndCountByExample(Example<Hospital> example);
}
