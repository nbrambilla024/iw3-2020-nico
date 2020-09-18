package ar.edu.iua.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Producto;
import ar.edu.iua.model.persistence.ProductoRepository;
import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class ProductoBusiness implements IProductoBusiness {

	@Autowired
	private ProductoRepository productoDAO;

	@Override
	public Producto load(Long id) throws NotFoundException, BusinessException {
		Optional<Producto> op;
		try {
			op = productoDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("El producto con el id " + id + "no fue encontrado");
		}
		return op.get();
	}

	@Override
	public List<Producto> list() throws BusinessException {
		try {
			return productoDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Producto add(Producto producto) throws BusinessException {
		try {
			return productoDAO.save(producto);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(Long id) throws NotFoundException, BusinessException {
		try {
			productoDAO.deleteById(id);
		} catch (EmptyResultDataAccessException e1) {
			throw new NotFoundException("No se encuentra el producto id=" + id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public Producto update(Producto producto) throws NotFoundException, BusinessException {
		Optional<Producto> op;
		try {
			op = productoDAO.findById(producto.getId());
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("El producto con el id " + producto.getId() + "no fue encontrado");
		}
		return productoDAO.save(producto);
	}

	@Override
	public List<Producto> listByParte(String parte) throws BusinessException {
		try {
			return productoDAO.findByNombreContainingOrDescripcionContaining(parte, parte);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Producto> listByPrecio(double precio, String precioOrden) throws BusinessException {
		try {
			if (precioOrden.equals("menor"))
				return productoDAO.findByPrecioListaLessThan(precio);

			return productoDAO.findByPrecioListaGreaterThan(precio);

		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public List<Producto> listByOrdenAscDesc(String orden) throws BusinessException {
		try {
			if (orden.equals("asc"))
				return productoDAO.findAllByOrderByPrecioListaAsc();

			return productoDAO.findAllByOrderByPrecioListaDesc();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Producto> listNombreByComienzaLetraA(String letra) throws BusinessException {
		
		try {
			if (letra.equals("A"))
				return productoDAO.findByNombreStartingWith(letra);
			
			return null;
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
