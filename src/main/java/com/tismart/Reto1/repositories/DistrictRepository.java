package com.tismart.Reto1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tismart.Reto1.models.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer>{

}
