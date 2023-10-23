package com.devsu.cuenta.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.model.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    
	List<Movimiento> findByCuenta(Cuenta cuenta);
    
    @Modifying
    @Query("DELETE FROM Movimiento m WHERE m.cuenta = ?1")
    void deleteByCuenta(Cuenta cuenta);
    
    @Query("SELECT m FROM Movimiento m WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Movimiento> findMovimientosByFechaBetween(Date fechaInicio, Date fechaFin);

    @Query("SELECT m FROM Movimiento m JOIN m.cuenta c WHERE (m.fecha BETWEEN :fechaInicio AND :fechaFin) AND c.idcliente = :clienteId")
	List<Movimiento> findMovimientosByFechaBetweenAndClienteId(Date fechaInicio, Date fechaFin, Long clienteId);
}
