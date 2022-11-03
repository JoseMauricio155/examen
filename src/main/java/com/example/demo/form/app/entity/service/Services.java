package com.example.demo.form.app.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.form.app.entity.Producto;
import com.example.demo.form.app.entity.Venta;
import com.example.demo.form.app.entity.dao.IDetalle_ventaDao;
import com.example.demo.form.app.entity.dao.IProductoDao;
import com.example.demo.form.app.entity.dao.IVentaDao;

@Service
public class Services implements IService{
	@Autowired
	private IVentaDao ventaDao;
	
	@Autowired 
	private IProductoDao productoDao;
	
	@Autowired 
	private IDetalle_ventaDao detalle_ventaDao;
	
	public Services() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(readOnly = true)
	public Venta findVentaById(Long id) {
		// TODO Auto-generated method stub
		return ventaDao.findById(id).orElse(null);
	}

	@Override
	public List<Producto> findByNombre(String term) {
		// TODO Auto-generated method stub
		return	productoDao.findByNombreLikeIgnoreCase("%"+term+"%");
	}

}
