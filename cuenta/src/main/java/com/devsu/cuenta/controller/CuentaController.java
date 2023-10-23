package com.devsu.cuenta.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.service.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
	
	private final CuentaService cuentaService;
	
	@Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }
	
	@PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        Cuenta nuevaCuenta = cuentaService.crearCuenta(cuenta);
        return new ResponseEntity<Cuenta>(nuevaCuenta, HttpStatus.CREATED);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.obtenerCuentaPorId(id);
        return new ResponseEntity<Cuenta>(cuenta, HttpStatus.OK);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        Cuenta cuentaActualizada = cuentaService.actualizarCuenta(id, cuenta);
        return new ResponseEntity<Cuenta>(cuentaActualizada, HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
	
	@GetMapping
    public ResponseEntity<List<Cuenta>> obtenerTodasLasCuentas() {
        List<Cuenta> cuentas = cuentaService.obtenerTodasLasCuentas();
        return new ResponseEntity<List<Cuenta>>(cuentas, HttpStatus.OK);
    }
}
