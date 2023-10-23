package com.devsu.cuenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.cuenta.model.Cuenta;


public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
	Cuenta findByNumeroCuenta(String numeroCuenta);
    List<Cuenta> findByEstado(boolean estado);
    List<Cuenta> findByTipoCuenta(String tipoCuenta);
    List<Cuenta> findByClienteId(Long idCliente);
}