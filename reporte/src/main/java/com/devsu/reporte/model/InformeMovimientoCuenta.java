package com.devsu.reporte.model;

import java.util.Date;

public class InformeMovimientoCuenta {
	
	private Date fecha;
    private String nombreCliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldoInicial;
    private boolean estadoCuenta;
    private double valorMovimiento;
    private double saldoDisponible;

    public InformeMovimientoCuenta() {}

    public InformeMovimientoCuenta(Date fecha, String nombreCliente, String numeroCuenta, String tipoCuenta, double saldoInicial, boolean estadoCuenta, double valorMovimiento, double saldoDisponible) {
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estadoCuenta = estadoCuenta;
        this.valorMovimiento = valorMovimiento;
        this.saldoDisponible = saldoDisponible;
    }
    
    public InformeMovimientoCuenta(MovimientoDto movimiento, CuentaDto cuenta, ClienteDto cliente) {
    	this.fecha = movimiento.getFecha();
        this.nombreCliente = cliente.getNombre();
        this.numeroCuenta = cuenta.getNumeroCuenta();
        this.tipoCuenta = cuenta.getTipoCuenta();
        this.saldoInicial = cuenta.getSaldoInicial();
        this.estadoCuenta = cuenta.isEstado();
        this.valorMovimiento = movimiento.getValor();
        this.saldoDisponible = movimiento.getSaldo();
    }
    
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public boolean isEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(boolean estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	public double getValorMovimiento() {
		return valorMovimiento;
	}

	public void setValorMovimiento(double valorMovimiento) {
		this.valorMovimiento = valorMovimiento;
	}

	public double getSaldoDisponible() {
		return saldoDisponible;
	}

	public void setSaldoDisponible(double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
}
