package com.example.demo.form.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	public LoginController() {
		// TODO Auto-generated constructor stub
	}
	@GetMapping("/login")
public String login(@RequestParam(value = "error",required = false) String error/*esta variable error no lo manda automaticamente spring cuando no son las creden.. corectas*/
		,@RequestParam(value = "logout",required = false) String logout
		,Model model,Principal principal,RedirectAttributes flash) {//principal nos permite validar
	if(principal != null) {
		flash.addFlashAttribute("info","Ya ha iniciado sesión anteriormente");
		//si principal ya ha iniciado sesión entonces
		return "redirect:/";
	}
	if(error != null) {// si hay un error
		model.addAttribute("error","Error en el login: Nombre de usuario y/o contraseña incorrecta, por favor vuelva a intentarlo!");
		
	}
	
	if(logout != null) {// si hay un error
		model.addAttribute("success","Ha cerrado sesión con exito");
		
	}
		
		return "login";
}
}
