package com.devsu.cuenta.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.devsu.cuenta.model.Movimiento;
import com.devsu.cuenta.service.MovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

	private final MovimientoService movimientoService;

    @Autowired
    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }
    
    @PostMapping
    public ResponseEntity<Movimiento> registrarMovimiento(@RequestBody Movimiento movimiento) {
        Movimiento nuevoMovimiento = movimientoService.registrarMovimiento(movimiento);
        return new ResponseEntity<Movimiento>(nuevoMovimiento, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Long id) {
        movimientoService.eliminarMovimientoPorID(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> actualizarMovimiento(@PathVariable Long id, @RequestBody Movimiento movimiento) {
        Movimiento movimientoActualizado = movimientoService.actualizarMovimiento(id, movimiento);
        return new ResponseEntity<Movimiento>(movimientoActualizado, HttpStatus.OK);
    }
    
    @GetMapping("/movimientosSoloPorRangoDeFechas")
    public List<Movimiento> obtenerMovimientosSoloPorRangoDeFechas(
        @RequestParam(name = "fechaInicio") Date fechaInicio,
        @RequestParam(name = "fechaFin") Date fechaFin) {
        return movimientoService.obtenerMovimientos(fechaInicio, fechaFin);
    }
    
    @GetMapping("/movimientosPorRangoDeFechas")
    public List<Movimiento> obtenerMovimientosPorRangoDeFechas(
        @RequestParam(name = "fechaInicio") Date fechaInicio,
        @RequestParam(name = "fechaFin") Date fechaFin,
    	@RequestParam(name = "clienteId") Long clienteId){
        return movimientoService.obtenerMovimientos(fechaInicio, fechaFin, clienteId);
    }
}
