package com.tismart.Reto1.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tismart.Reto1.models.Manager;
import com.tismart.Reto1.repositories.ManagerRepository;
import com.tismart.Reto1.services.IManagerService;

@Service
public class ManagerServiceImpl implements IManagerService{

	@Autowired
	private ManagerRepository managerRepository;
	
	@Override
	public List<Manager> findAllManagers() {
	//public Page<Manager> findAllManagers(Pageable page) {
		return managerRepository.findAll();
	}

	@Override
	public void saveManager(Manager manager) {
		managerRepository.save(manager);		
	}

	@Override
	public Manager findManagerById(Integer idManager) {
		
		Optional<Manager> optional = managerRepository.findById(idManager);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		return null;
	}

	@Override
	public void deleteManagerById(Integer idManager) {
		managerRepository.deleteById(idManager);		
	}

	@Override
	public List<Manager> findManagersWithoutHospital() {		
		return managerRepository.findManagersWithoutHospital();
	}

}
