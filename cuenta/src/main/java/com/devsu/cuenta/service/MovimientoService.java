package com.devsu.cuenta.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.cuenta.exception.MovimientoNotFoundException;
import com.devsu.cuenta.exception.SaldoInsuficienteException;
import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.model.Movimiento;
import com.devsu.cuenta.repository.MovimientoRepository;

@Service
public class MovimientoService {
	
	private final MovimientoRepository movimientoRepository;
	private final CuentaService cuentaService;

    @Autowired
    public MovimientoService(MovimientoRepository movimientoRepository, CuentaService cuentaService) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaService = cuentaService;
    }

    @Transactional
    public Movimiento registrarMovimiento(Movimiento movimiento) {

    	if (movimiento.getValor() < 0) {
            Cuenta cuenta = movimiento.getCuenta();
            double saldoDisponible = cuenta.getSaldoInicial() + cuentaService.calcularSaldoActual(cuenta);
            
            if (saldoDisponible + movimiento.getValor() < 0) {
                throw new SaldoInsuficienteException("Saldo insuficiente (" + saldoDisponible + ") en la cuenta " + cuenta.getNumeroCuenta() + " para realizar el movimiento " + movimiento.getValor() + ".");
            }
        }
    	
        return movimientoRepository.save(movimiento);
    }
    
    @Transactional
    public Movimiento actualizarMovimiento(Long movimientoId, Movimiento movimiento) {
    	Optional<Movimiento> movimientoExistente = movimientoRepository.findById(movimientoId);
        if (movimientoExistente.isPresent()) {
        	movimiento.setId(movimientoId);
            return movimientoRepository.save(movimiento);
        } else {
            throw new MovimientoNotFoundException("Movimiento no encontrado con ID: " + movimientoId);
        }
    }
    
    @Transactional
    public void eliminarMovimientosDeCuenta(Cuenta cuenta) {
        movimientoRepository.deleteByCuenta(cuenta);
    }
    
    @Transactional
    public void eliminarMovimientoPorID(Long movimientoId) {
    	movimientoRepository.deleteById(movimientoId);
    }
    
    public List<Movimiento> obtenerMovimientos(Date fechaInicio, Date fechaFin){
    	return movimientoRepository.findMovimientosByFechaBetween(fechaInicio, fechaFin);
    }

	public List<Movimiento> obtenerMovimientos(Date fechaInicio, Date fechaFin, Long clienteId) {
		return movimientoRepository.findMovimientosByFechaBetweenAndClienteId(fechaInicio, fechaFin, clienteId);
	}
}
