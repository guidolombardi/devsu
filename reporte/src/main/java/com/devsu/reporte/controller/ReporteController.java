package com.devsu.reporte.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.reporte.model.InformeMovimientoCuenta;
import com.devsu.reporte.service.ReporteService;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

	private final ReporteService reporteService;

    @Autowired
    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }
	
    @GetMapping
    public List<InformeMovimientoCuenta> obtenerMovimientosPorRangoDeFechas(
        @RequestParam(name = "fechaInicio") Date fechaInicio,
        @RequestParam(name = "fechaFin") Date fechaFin,
    	@RequestParam(name = "clienteId") Long clienteId){
        return reporteService.generarReporteEstadoCuenta(fechaInicio, fechaFin, clienteId);
    }
    
}
