package com.tismart.Reto1.services;

import java.util.List;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;

import com.tismart.Reto1.models.Manager;

public interface IManagerService {
	
	List<Manager> findAllManagers();
	List<Manager> findManagersWithoutHospital();
	void saveManager(Manager manager);
	Manager findManagerById(Integer idManager);
	void deleteManagerById(Integer idManager);
	
	//Page<Manager> findAllManagers(Pageable page);
}
