package ar.edu.iua.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "venta_detalle")
public class VentaDetalle implements Serializable {

	private static final long serialVersionUID = 7919545130670761876L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 100, nullable = false)
	private Double precioTotal;

	@Column(length = 100, nullable = false)
	private int cantidad;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "venta_id")
	private Venta venta;
	

	
	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
