package ar.edu.iua.business;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Venta;
import ar.edu.iua.model.persistence.VentaRepository;

@Service
public class VentaBusiness implements IVentaBusiness{

	@Autowired
	private VentaRepository ventaDAO;
	
	@Override
	public Venta load(Integer id) throws NotFoundException, BusinessException {
		Optional<Venta> op;
		try {
			op = ventaDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("La venta con id " + id + " no se encuentra en la BD");
		}
		return op.get();
	}

	@Override
	public List<Venta> list() throws BusinessException {

		try {
			return ventaDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Venta add(Venta venta) throws BusinessException {
		try {
			return ventaDAO.save(venta);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Venta update(Venta venta) throws NotFoundException, BusinessException {
		load(venta.getId());
		return add(venta);
	}
	
	@Override
	public void delete(Integer id) throws NotFoundException, BusinessException {
		try {
			ventaDAO.deleteById(id);
		} catch (EmptyResultDataAccessException e1) {
			throw new NotFoundException("No se encuentra la venta con id=" + id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}
}
