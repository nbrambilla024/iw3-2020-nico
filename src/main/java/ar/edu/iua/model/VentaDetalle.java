package ar.edu.iua.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "venta_detalle")
public class VentaDetalle implements Serializable{

	private static final long serialVersionUID = 7919545130670761876L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
