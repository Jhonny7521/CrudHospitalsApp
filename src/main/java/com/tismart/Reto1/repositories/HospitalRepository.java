package com.tismart.Reto1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tismart.Reto1.models.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer>{

}
