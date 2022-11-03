package com.example.demo.form.app.entity.service;

import java.util.List;

import com.example.demo.form.app.entity.Producto;
import com.example.demo.form.app.entity.Venta;


public interface IService {
	public Venta findVentaById(Long id);
	public List<Producto> findByNombre(String term);
	
}
