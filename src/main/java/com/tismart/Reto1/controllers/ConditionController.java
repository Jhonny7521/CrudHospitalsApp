package com.tismart.Reto1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tismart.Reto1.models.Condition;
import com.tismart.Reto1.services.IConditionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/condition")
public class ConditionController {

	@Autowired
	private IConditionService conditionService;
	
	@GetMapping("/")
	public String conditionHome(Model model) {
		List<Condition> conditionList = conditionService.findAllConditions();
		model.addAttribute("conditions", conditionList);
		return "condition/index";
	}
	
	@GetMapping("/new")
	public String newCondition() {
		return "condition/newCondition";
	}
	
	@PostMapping("/save")
	public String saveCondition(@Valid @ModelAttribute("condition") Condition condition, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}
			return "condition/newCondition";
		}
		
		try {
			conditionService.saveCondition(condition);
			attributes.addFlashAttribute("message", "La condición fue guardada correctamente");	
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error al guardar la condición.");
	        return "condition/newCondition";
		}
		
		return "redirect:/condition/";
	}
	
	@GetMapping("/edit/{id}")
	public String editCondition(@PathVariable("id") int id, Model model, RedirectAttributes attributes) {
		
		try {
			Condition condition = conditionService.findConditionById(id);
			
			if(condition != null) {
				model.addAttribute("condition", condition);
				return "condition/editCondition";			
			}
			
			attributes.addFlashAttribute("message_error", "La condición no fue encontrada");
			return "redirect:/condition/";
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con el registro");
			return "redirect:/condition/";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCondition(@PathVariable("id") int id, RedirectAttributes attributes) {
		try {
			conditionService.deleteConditionById(id);
			attributes.addFlashAttribute("message", "La condición con fue eliminada");
			return "redirect:/condition/";
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con el registro");
			return "redirect:/condition/";
		}
	}
	
	
	@ModelAttribute
	public void setGenericConditionData(Model model) {
		Condition condition = new Condition();
		model.addAttribute("condition", condition);
	}
}
