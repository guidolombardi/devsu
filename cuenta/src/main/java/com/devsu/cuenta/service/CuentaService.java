package com.devsu.cuenta.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.cuenta.exception.CuentaNotFoundException;
import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.model.Movimiento;
import com.devsu.cuenta.repository.CuentaRepository;

@Service
public class CuentaService {

	private final CuentaRepository cuentaRepository;
	private final MovimientoService movimientoService;

    @Autowired
    public CuentaService(CuentaRepository cuentaRepository, MovimientoService movimientoService) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoService = movimientoService;
    }
    
    public Cuenta obtenerCuentaPorId(Long cuentaId) {
    	Optional<Cuenta> cuentaExistente = cuentaRepository.findById(cuentaId);
        if (!cuentaExistente.isPresent()) {
        	throw new CuentaNotFoundException("Cuenta no encontrada con ID: " + cuentaId);
        }
        return cuentaExistente.get();
    }    
    
    @Transactional
    public Cuenta crearCuenta(Cuenta cuenta) {
        Cuenta nuevaCuenta = cuentaRepository.save(cuenta);
        
        if(nuevaCuenta.getSaldoInicial()>0) {        
	        Movimiento movimientoInicial = new Movimiento("Saldo Inicial", cuenta.getSaldoInicial(), cuenta.getSaldoInicial());
	        movimientoInicial.setCuenta(nuevaCuenta);
	        movimientoService.registrarMovimiento(movimientoInicial);
        }
        
        return nuevaCuenta;
    }
    
    @Transactional
    public void eliminarCuenta(Long cuentaId) {
    	Optional<Cuenta> cuentaExistente = cuentaRepository.findById(cuentaId);
        if (cuentaExistente.isPresent()) {
        	movimientoService.eliminarMovimientosDeCuenta(cuentaExistente.get());
        	cuentaRepository.deleteById(cuentaId);
        } else {
            throw new CuentaNotFoundException("Cuenta no encontrada con ID: " + cuentaId);
        }
    }
    
    @Transactional
    public Cuenta actualizarCuenta(Long cuentaId, Cuenta cuenta) {
    	Optional<Cuenta> cuentaExistente = cuentaRepository.findById(cuentaId);
        if (cuentaExistente.isPresent()) {
        	cuenta.setId(cuentaId);
            return cuentaRepository.save(cuenta);
        } else {
            throw new CuentaNotFoundException("Cuenta no encontrada con ID: " + cuentaId);
        }
    }   
    
    public List<Cuenta> obtenerCuentasPorIdCliente(Long idCliente) {
        return cuentaRepository.findByClienteId(idCliente);
    }
    
    public double calcularSaldoActual(Cuenta cuenta) {
        double saldoInicial = cuenta.getSaldoInicial();
        double saldoActual = saldoInicial;

        List<Movimiento> movimientos = cuenta.getMovimientos();

        for (Movimiento movimiento : movimientos) {
            saldoActual += movimiento.getValor();
        }

        return saldoActual;
    }

	public List<Cuenta> obtenerTodasLasCuentas() {
		return cuentaRepository.findAll();
	}
}
