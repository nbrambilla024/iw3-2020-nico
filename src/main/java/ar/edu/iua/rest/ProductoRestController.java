package ar.edu.iua.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.iua.business.IProductoBusiness;
import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Producto;

@RestController
@RequestMapping(value = Constantes.URL_PRODUCTOS)
public class ProductoRestController {

	@Autowired
	private IProductoBusiness productoBusiness;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> load(@PathVariable("id") Long id) {

		try {
			return new ResponseEntity<Producto>(productoBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> list(
			@RequestParam(name = "parte", required = false, defaultValue = "*") String parte,
			@RequestParam(name = "precio", required = false, defaultValue = "-1") String precio,
			@RequestParam(name = "precio-orden", required = false, defaultValue = "mayor") String precioOrden,
			@RequestParam(name = "orden", required = false, defaultValue = "asc") String orden,
			@RequestParam(name = "letra", required = false, defaultValue = "*") String letra

	) {
		try {
			if (!parte.equals("*"))
				return new ResponseEntity<List<Producto>>(productoBusiness.listByParte(parte), HttpStatus.OK);
			if (!precio.equals("-1"))
				return new ResponseEntity<List<Producto>>(
						productoBusiness.listByPrecio(Double.parseDouble(precio), precioOrden), HttpStatus.OK);
			if (!orden.equals("*"))
				return new ResponseEntity<List<Producto>>(productoBusiness.listByOrdenAscDesc(orden), HttpStatus.OK);
			if (!letra.equals("*"))
				return new ResponseEntity<List<Producto>>(productoBusiness.listNombreByComienzaLetraA(letra),
						HttpStatus.OK);

			return new ResponseEntity<List<Producto>>(productoBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Producto producto) {
		try {
			productoBusiness.add(producto);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_PRODUCTOS + "/" + producto.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			// log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody Producto producto) {
		try {
			productoBusiness.update(producto);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			// log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			productoBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			// log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

}
