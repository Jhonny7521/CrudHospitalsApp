package com.tismart.Reto1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tismart.Reto1.models.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer>{
	
	@Query("SELECT m FROM Manager m LEFT JOIN m.hospital h WHERE h.id IS NULL")
    List<Manager> findManagersWithoutHospital();
}
