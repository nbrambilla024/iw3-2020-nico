package ar.edu.iua.model.persistence;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.iua.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

	public List<Producto> findByNombreContaining(String nombre);

	public List<Producto> findByDescripcionContaining(String descripcion);

	public List<Producto> findByPrecioListaGreaterThan(Double precio);

	public List<Producto> findByPrecioListaLessThan(Double precio);

	public List<Producto> findByNombreContainingOrDescripcionContaining(String nombre, String descripcion);

	public List<Producto> findAllByOrderByPrecioListaAsc();

	public List<Producto> findAllByOrderByPrecioListaDesc();

	public List<Producto> findByNombreStartingWith(String letra);

}
