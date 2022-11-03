package com.example.demo.form.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;






@Entity
@Table(name = "ventas")
public class Venta  implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Temporal(TemporalType.DATE)
@Column(name = "create_at")
private Date createAt;
@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
@JoinColumn(name = "venta_id")//vamos a tener esta clave foranea en item_factura
private List<Detalle_Venta> items;
	public Venta() {
		// TODO Auto-generated constructor stub
		this.items=new ArrayList<Detalle_Venta>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public List<Detalle_Venta> getItems() {
		return items;
	}
	public void setItems(List<Detalle_Venta> items) {
		this.items = items;
	}
	
	public void addItemVenta(Detalle_Venta item) {
		this.items.add(item);
	}
	public Double getTotal() {
		Double total=0.0;
		int size=items.size();
		for (int i = 0; i < size; i++) {
			total+=items.get(i).getImporte();
		}
		return total;
	}
	

}
