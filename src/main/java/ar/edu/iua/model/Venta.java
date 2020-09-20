package ar.edu.iua.model;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "venta")
public class Venta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1861830004923572175L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name ="fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fecha;

	@Column(length = 150, nullable = false)
	private Double precioTotalVenta;

	@Column(length = 120, nullable = false)
	private int cantTotalProductos;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(targetEntity = VentaDetalle.class, mappedBy = "venta", fetch = FetchType.LAZY)
	private List<VentaDetalle> ventaDetalleList;
	 
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getPrecioTotal() {
		return precioTotalVenta;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotalVenta = precioTotal;
	}

	public int getCantTotalProductos() {
		return cantTotalProductos;
	}

	public void setCantTotalProductos(int cantTotalProductos) {
		this.cantTotalProductos = cantTotalProductos;
	}

}
