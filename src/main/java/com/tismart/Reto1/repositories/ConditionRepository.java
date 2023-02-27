package com.tismart.Reto1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tismart.Reto1.models.Condition;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Integer>{

}
