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

import com.tismart.Reto1.models.District;
import com.tismart.Reto1.services.IDistrictService;
import com.tismart.Reto1.services.IProvinceService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/district")
public class DistrictController {
	
	@Autowired
	private IDistrictService districtService;
	
	@Autowired
	private IProvinceService provinceService;
	
	@GetMapping("/")
	public String districtHome(Model model) {
		List<District> districtList = districtService.findAllDistricts();
		model.addAttribute("districts", districtList);
		return "district/index";
	}
	
	@GetMapping("/new")
	public String newDistrict() {
		return "district/newDistrict";
	}
	
	@PostMapping("/save")
	public String saveDistrict(@Valid @ModelAttribute("district") District district, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}
			return "district/newDistrict";
		}
		
		try {
			districtService.saveDistrict(district);
			attributes.addFlashAttribute("message", "El distrito se guardó correctamente");
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error al guardar la condición");
			return "district/newDistrict";
		}
		
		return "redirect:/district/";
	}
	
	@GetMapping("/edit/{id}")
	public String editDistrici(@PathVariable("id") int id, Model model, RedirectAttributes attributes) {
		
		try {
			District district = districtService.findDistrictById(id);

			if( district != null) {
				model.addAttribute("district", district);
				return "district/editDistrict";				
			}
			
			attributes.addFlashAttribute("message_error", "El distrito no fue encontrado");
			return "redirect:/district/";
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con el registro");
			return "redirect:/district/";
		}
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteDistrict(@PathVariable("id") int id, RedirectAttributes attributes) {
		
		try {
			districtService.deleteDistrictById(id);
			attributes.addFlashAttribute("message", "El distrito se ha eliminado");
			return "redirect:/district/";
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con el registro");
			return "redirect:/district/";
		}
		
	}
	
	@ModelAttribute
	public void setGenericDistrictData(Model model) {
		District district = new District();
		model.addAttribute("district", district);
		model.addAttribute("provinces", provinceService.findAllProvinces());
		
	}

}
