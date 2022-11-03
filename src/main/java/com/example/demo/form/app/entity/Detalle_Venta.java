package com.example.demo.form.app.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "detalle_ventas")
public class Detalle_Venta  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer cantidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "producto_id")//en este caso lo especificamos de forma
	// explisita a unque no es necesario ya que arriba existe la relacion
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Producto producto;// por defecto va crear la foreingkey

	public Detalle_Venta() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Double getImporte() {
		return cantidad.doubleValue()*producto.getPrecio();
	}

	@Override
	public String toString() {
		return  ""+getImporte();
	}
	
}
