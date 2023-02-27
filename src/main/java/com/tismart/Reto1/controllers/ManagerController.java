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

import com.tismart.Reto1.models.Manager;
import com.tismart.Reto1.services.IManagerService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	private IManagerService managerService;
	
	@GetMapping("/")
	public String managerHome(Model model) {
		List<Manager> managerList = managerService.findAllManagers();
		model.addAttribute("managers", managerList);
		
		return "manager/index";
	}
	
	@GetMapping("/new")
	public String newManager() {
		return "manager/newManager";
	}
	
	@PostMapping("/save")
	public String saveManager(@Valid @ModelAttribute("manager") Manager manager, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}
			return "manager/newManager";
		}
				
		try {
	        managerService.saveManager(manager);
	        attributes.addFlashAttribute("message", "El gerente fue guardado correctamente!");
	        
	    } catch (Exception e) {
	        attributes.addFlashAttribute("message_error", "Ocurrió un error al guardar el gerente.");
	        return "manager/newManager";
	    }

		return "redirect:/manager/";
	}
	
	@GetMapping("/edit/{id}")
	public String updateManager(@PathVariable("id") int id, Model model, RedirectAttributes attributes) {
		try {
			Manager manager = managerService.findManagerById(id);
			
			if (manager != null) {
				model.addAttribute("manager", manager);
				return "manager/editManager";					
			}
			
			attributes.addFlashAttribute("message_error","El registro no fue encontrado");
			return "redirect:/manager/";
						
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error","Ocurrió un error con el registro");
			return "redirect:/manager/";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String deleteManager(@PathVariable("id") int id, RedirectAttributes attributes) {
		try {
			managerService.deleteManagerById(id);
			attributes.addFlashAttribute("message","El gerente fue eliminado");
			return "redirect:/manager/";
			
		} catch (Exception e){
			attributes.addFlashAttribute("message_error","Ocurrió un error con el registro");
			return "redirect:/manager/";
		}
	}
	
	@ModelAttribute
	public void setGenericManagerData(Model model) {
		Manager manager = new Manager();
		model.addAttribute("manager", manager);
	}

}
