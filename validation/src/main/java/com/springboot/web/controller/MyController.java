package com.springboot.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.web.entities.LoginData;

@Controller
public class MyController {
	
	@GetMapping("/form-open")
	public String openform(Model m) {
		m.addAttribute("loginData",new LoginData());
		System.out.println("openeing form");
		return "form";
	}
	
	@PostMapping("/process")
	public String success(@Valid @ModelAttribute("loginData") LoginData loginData,BindingResult result) {
        if( result.hasErrors() ){
            System.out.println("The result count is " + result);
			System.out.println("helllooooooooooooooo");
			return "form";
        }
	
		return "success";
	}
	
}
