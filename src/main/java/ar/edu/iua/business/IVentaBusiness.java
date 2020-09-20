package ar.edu.iua.business;

import java.util.List;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Venta;

public interface IVentaBusiness {

	public Venta load(Integer id) throws NotFoundException, BusinessException;

	public List<Venta> list() throws BusinessException;

	public Venta add(Venta venta) throws BusinessException;

	public Venta update(Venta venta) throws NotFoundException, BusinessException;

	public void delete(Integer id) throws NotFoundException, BusinessException;

}
