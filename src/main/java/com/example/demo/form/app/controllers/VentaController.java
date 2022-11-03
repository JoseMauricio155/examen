package com.example.demo.form.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.form.app.entity.Venta;
import com.example.demo.form.app.entity.service.IService;


@Controller
public class VentaController {
@Autowired
private IService service;
	public VentaController() {
		// TODO Auto-generated constructor stub
	}
	@GetMapping("/")
	public String mostrar() {
		return "venta";
	}
	
@GetMapping({"/venta/{id}"})
public @ResponseBody Venta show(@PathVariable(value = "id") Long id/*Model model*/) {
	Venta venta=service.findVentaById(id);
	//model.addAttribute("venta", venta);
	return venta;
}


@GetMapping(value = "/detalle/{term}",produces = {"application/json"})//con produces es una salida de tipo aplication json
public @ResponseBody Venta cargarProductos(@PathVariable Long term){//con Responsebody suprime la vista de thymeleaf y toma solo la respuesta convertido en json y va a poblar la respuesta
	
	return service.findVentaById(term);
}
}
