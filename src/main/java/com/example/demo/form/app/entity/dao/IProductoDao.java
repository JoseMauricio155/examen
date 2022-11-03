package com.example.demo.form.app.entity.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.form.app.entity.Producto;



public interface IProductoDao extends CrudRepository<Producto, Long>{
	public List<Producto> findByNombreLikeIgnoreCase(String term);
}
