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

import com.tismart.Reto1.models.Location;
import com.tismart.Reto1.services.ILocationService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/location")
public class LocationController {

	@Autowired
	public ILocationService locationService;
	
	@GetMapping("/")
	public String locationHome(Model model) {
		List<Location> locationList = locationService.findAllLocations();
		model.addAttribute("locations", locationList);
		return "location/index";
	}
	
	@GetMapping("/new")
	public String newLocation(Model model) {
		return "location/newLocation";
	}
	
	@PostMapping("/save")
	public String saveLocation(@Valid @ModelAttribute("location") Location location, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}
			return "location/newLocation";
		}
		
		try {
			locationService.saveLocation(location);
			attributes.addFlashAttribute("message", "La sede fue guardada correctamente");
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error al guardar la sede");
			return "location/newLocation";
		}
		
		return "redirect:/location/";
	}
	
	@GetMapping("/edit/{id}")
	public String editLocation(@PathVariable("id") int id, Model model, RedirectAttributes attributes) {
		
		try {
			Location location = locationService.findLocationById(id);
			
			if(location != null) {
				model.addAttribute("location", location);
				return "location/editLocation";				
			}
			
			attributes.addFlashAttribute("message_error", "La sede no fue encontrada");
			return "redirect:/location/";
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con el registro");
			return "redirect:/location/";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String deleteLocation(@PathVariable("id") int id, RedirectAttributes attributes) {
		try {
			locationService.deleteLocationById(id);
			attributes.addFlashAttribute("message", "La sede fue eliminada");
			return "redirect:/location/";
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con el registro");
			return "redirect:/location/";
		}
	}
	
	@ModelAttribute
	public void getGenericLocationData(Model model) {
		Location location = new Location();
		model.addAttribute("location", location);
	}
	
}
