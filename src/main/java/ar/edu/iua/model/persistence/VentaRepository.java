package ar.edu.iua.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;



import org.springframework.stereotype.Repository;

import ar.edu.iua.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {


}
