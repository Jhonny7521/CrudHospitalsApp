package com.tismart.Reto1.services;

import java.util.List;

import com.tismart.Reto1.models.Condition;

public interface IConditionService {
	
	List<Condition> findAllConditions();
	void saveCondition(Condition condition);
	Condition findConditionById(int idCondition);
	void deleteConditionById(int idCondition);
	//Page<Manager> findAllConditions(Pageable page);
}
