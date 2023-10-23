package com.devsu.reporte.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devsu.reporte.model.ClienteDto;
import com.devsu.reporte.model.CuentaDto;
import com.devsu.reporte.model.InformeMovimientoCuenta;
import com.devsu.reporte.model.MovimientoDto;

@Service
public class ReporteService {
	@Autowired
    private RestTemplate restTemplate;
	private final String urlHost = "";
	
	public List<InformeMovimientoCuenta> generarReporteEstadoCuenta(Date fechaInicio, Date fechaFin) {
        String movimientoUrl = urlHost + "/movimientos/movimientosPorRangoDeFechas?fechaInicio=" + fechaInicio + "&fechaFin=" + fechaFin;
        MovimientoDto[] movimientos = restTemplate.getForObject(movimientoUrl, MovimientoDto[].class);
        
        List<InformeMovimientoCuenta> informes = new ArrayList<InformeMovimientoCuenta>();
        
        for (MovimientoDto movimiento : movimientos) {
            String cuentaUrl = urlHost + "/cuentas/" + movimiento.getCuentaId();
            CuentaDto cuenta = restTemplate.getForObject(cuentaUrl, CuentaDto.class);
            
            String clienteUrl = urlHost + "/clientes/" + cuenta.getIdcliente();
            ClienteDto cliente = restTemplate.getForObject(clienteUrl, ClienteDto.class);
            
            InformeMovimientoCuenta informe = new InformeMovimientoCuenta(movimiento, cuenta, cliente);
            informes.add(informe);
        }

        return informes;
    }
	
	public List<InformeMovimientoCuenta> generarReporteEstadoCuenta(Date fechaInicio, Date fechaFin, Long clienteId) {
        String movimientoUrl = urlHost + "/movimientos/movimientosPorRangoDeFechas?fechaInicio=" + fechaInicio + "&fechaFin=" + fechaFin + "&clienteId=" + clienteId;
        MovimientoDto[] movimientos = restTemplate.getForObject(movimientoUrl, MovimientoDto[].class);
        
        String clienteUrl = urlHost + "/clientes/" + clienteId;
        ClienteDto cliente = restTemplate.getForObject(clienteUrl, ClienteDto.class);
        
        List<InformeMovimientoCuenta> informes = new ArrayList<InformeMovimientoCuenta>();
                
        for (MovimientoDto movimiento : movimientos) {
            String cuentaUrl = urlHost + "/cuentas/" + movimiento.getCuentaId();
            CuentaDto cuenta = restTemplate.getForObject(cuentaUrl, CuentaDto.class);
            
            InformeMovimientoCuenta informe = new InformeMovimientoCuenta(movimiento, cuenta, cliente);
            informes.add(informe);
        }

        return informes;
    }
	
}
