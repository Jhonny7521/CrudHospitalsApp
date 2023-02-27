package com.tismart.Reto1.controllers;

//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tismart.Reto1.models.Hospital;
import com.tismart.Reto1.services.IConditionService;
import com.tismart.Reto1.services.IDistrictService;
import com.tismart.Reto1.services.IHospitalService;
import com.tismart.Reto1.services.ILocationService;
import com.tismart.Reto1.services.IManagerService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/hospital")
public class HospitalController {
	
	@Autowired
	private IHospitalService hospitalService;
	
	@Autowired
	private IManagerService managerService;
	
	@Autowired
	private IConditionService conditionService;
	
	@Autowired
	private IDistrictService districtService;
	
	@Autowired
	private ILocationService locationService;
	
	@GetMapping("/home")
	public String home() {
		return "index";
	}
	
	@GetMapping("/find")
	public String hospitalHome(Model model) {
		List<Hospital> hospitalList = hospitalService.findAllHospitals();
		long count = hospitalService.count();
		String numberOfHospitals = "Existen " + String.valueOf(count) + " hospital(es)";
		model.addAttribute("hospitals", hospitalList);
		model.addAttribute("numberOfHospitals", numberOfHospitals);
		return "hospital/index";
	}
	
	@GetMapping("/search")
	public String hospitalSearch(@ModelAttribute("hospitalSearch") Hospital hospital, Model model, RedirectAttributes attributes) {
		
		try {
			ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("hospitalName", ExampleMatcher.GenericPropertyMatchers.contains());
			
			Example<Hospital> example = Example.of(hospital,matcher);
			List<Hospital> hospitalList = hospitalService.findByExample(example);
			long count = hospitalService.findAndCountByExample(example);
			
			if (hospitalList.isEmpty()) {
				
				attributes.addFlashAttribute("message_error", "No se encontraron registros que conincidan con el campo de búsqueda");
				return "redirect:/hospital/find";
			}
			
			String numberOfHospitals = "Existen " + String.valueOf(count) + " hospital(es)";
			model.addAttribute("hospitals", hospitalList);
			model.addAttribute("numberOfHospitals", numberOfHospitals);
			return "hospital/index";
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con la búsqueda");
			return "hospital/index";
		}
		
	}
	
	@GetMapping("/new")
	public String newHospital() {
		return "hospital/newHospital";
	}
	
	@PostMapping("/save")
	public String saveHospital(@Valid @ModelAttribute("hospital") Hospital hospital, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			System.out.println(hospital);
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}			
			return "hospital/newHospital";
		}
		        
		try {
			hospitalService.saveHospital(hospital);
			attributes.addFlashAttribute("message", "El hospital fue guardado correctamente!");
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error al guardar el hospital");
			return "hospital/newHospital";
		}
		
		return "redirect:/hospital/find";
	}
	
	@GetMapping("/edit")
	public String editHospital() {
		return "hospital/editHospital";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteHospital(@PathVariable("id") int id, RedirectAttributes attributes) {
		try {
			hospitalService.deleteHospitalById(id);
			attributes.addFlashAttribute("message", "El hospital se ha eliminado");
			return "redirect:/hospital/home";
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con el registro");
			return "redirect:/hospital/home";
		}
	}
	
	@GetMapping("/list")
	public String deleteHospital(Model model) {
		List<Hospital> hospitalList = hospitalService.findAllHospitals();
		long count = hospitalService.count();
		String numberOfHospitals = "Existen " + String.valueOf(count) + " hospital(es)";
		model.addAttribute("hospitals", hospitalList);
		model.addAttribute("numberOfHospitals", numberOfHospitals);
		return "hospital/deleteHospital";
	}
	
	@GetMapping("/searchId")
	public String hospitalSearchById(@RequestParam("hospitalId") int id, Model model, RedirectAttributes attributes) {
		
		try {
			Hospital hospital = hospitalService.finHospitalById(id);
			
			if(hospital != null) {
				model.addAttribute("hospital", hospital);		
				return "hospital/editHospital";			
			}
			
			attributes.addFlashAttribute("message_error", "Hospital no encontrado");
			return "redirect:/hospital/edit";			
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error en la búsqueda");
			return "redirect:/hospital/edit";
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@ModelAttribute
	public void setGenericHospitalData(Model model) {
		Hospital hospitalObj = new Hospital();
		model.addAttribute("hospital", hospitalObj);
		model.addAttribute("hospitalSearch", hospitalObj);
		model.addAttribute("conditions", conditionService.findAllConditions());
		model.addAttribute("districts", districtService.findAllDistricts());
		model.addAttribute("locations", locationService.findLocationsWithoutHospital());
		model.addAttribute("managers", managerService.findManagersWithoutHospital());
	}
}
