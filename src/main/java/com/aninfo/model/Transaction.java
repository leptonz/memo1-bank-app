
package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numeroDeTransacccion;

    private Double monto;

    private String tipoDeTransaccion;

    private Long cbuCuenta;

    public Long getCbuCuenta() {
        return cbuCuenta;
    }

    public void setCbuCuenta(Long cbuCuenta) {
        this.cbuCuenta = cbuCuenta;
    }

    public Long getNumeroDeTransacccion() {
        return numeroDeTransacccion;
    }

    public void setNumeroDeTransacccion(Long numeroDeTransacccion) {
        this.numeroDeTransacccion = numeroDeTransacccion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getTipoDeTransaccion() {
        return tipoDeTransaccion;
    }

    public void setTipoDeTransaccion(String tipoDeTransaccion) {
        this.tipoDeTransaccion = tipoDeTransaccion;
    }
}
