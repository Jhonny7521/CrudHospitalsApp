package com.tismart.Reto1.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tismart.Reto1.models.Condition;
import com.tismart.Reto1.repositories.ConditionRepository;
import com.tismart.Reto1.services.IConditionService;

@Service
public class ConditionServiceImpl implements IConditionService{
	
	@Autowired
	private ConditionRepository conditionRepository;

	@Override
	public List<Condition> findAllConditions() {
		return conditionRepository.findAll();
	}

	@Override
	public void saveCondition(Condition condition) {
		conditionRepository.save(condition);
	}

	@Override
	public Condition findConditionById(int idCondition) {
		Optional<Condition> optional = conditionRepository.findById(idCondition);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void deleteConditionById(int idCondition) {
		conditionRepository.deleteById(idCondition);
	}

}
