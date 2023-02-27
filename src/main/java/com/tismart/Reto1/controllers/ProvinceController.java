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

import com.tismart.Reto1.models.Province;
import com.tismart.Reto1.services.IProvinceService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/province")
public class ProvinceController {

	@Autowired
	private IProvinceService provinceService;
	
	@GetMapping("/")
	public String provinceHome(Model model) {
		List<Province> provinceList = provinceService.findAllProvinces();
		model.addAttribute("provinces", provinceList);
		return "province/index";
	}
	
	@GetMapping("/new")
	public String newProvince() {
		return "province/newProvince";
	}
	
	@PostMapping("/save")
	public String saveProvince(@Valid @ModelAttribute("province") Province province, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}
			return "province/newProvince";
		}
		
		try {
			provinceService.saveProvince(province);
			attributes.addFlashAttribute("message", "La provincia se agregó correctamente");
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error al guardar la provincia");
			return "province/newProvince";
		}
		
		return "redirect:/province/";
	}
	
	@GetMapping("/edit/{id}")
	public String editProvince(@PathVariable("id") int id, Model model, RedirectAttributes attributes) {
		
		try {
			Province province = provinceService.findProvinceById(id);
			
			if (province != null) {
				model.addAttribute("province", province);
				return "province/editProvince";				
			}
			
			attributes.addFlashAttribute("message_error", "La provincia no fue encontrada");
			return "redirect:/province/";
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con el registro");
			return "redirect:/province/";
		}
	}
		
	@GetMapping("/delete/{id}")
	public String deleteProvince(@PathVariable("id") int id, RedirectAttributes attributes) {
		
		try {
			provinceService.deleteProvinceById(id);
			attributes.addFlashAttribute("message", "El servicio se ha eliminado");
			return "redirect:/province/";
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con el registro");
			return "redirect:/province/";
		}
	}
	
	@ModelAttribute
	public void setGenericProvinceData(Model model) {
		Province province = new Province();
		model.addAttribute("province", province);
	}
}
