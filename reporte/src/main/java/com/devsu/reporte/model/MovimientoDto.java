package com.devsu.reporte.model;

import java.util.Date;

public class MovimientoDto {

    private Long id;

    private Date fecha;

    private String tipoMovimiento;
    private double valor;
    private double saldo;
    private Long cuentaId;

    public MovimientoDto() {
        this.fecha = new Date();
    }

    public MovimientoDto(String tipoMovimiento, double valor, double saldo) {
        this.fecha = new Date();
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldo = saldo;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Long getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(Long cuentaId) {
		this.cuentaId = cuentaId;
	}	
		
}
