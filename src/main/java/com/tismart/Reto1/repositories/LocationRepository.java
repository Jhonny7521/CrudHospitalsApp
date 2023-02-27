package com.tismart.Reto1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tismart.Reto1.models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>{
	
	@Query("SELECT l FROM Location l LEFT JOIN l.hospital h WHERE h.id IS NULL")
    List<Location> findLocationsWithoutHospital();
}
