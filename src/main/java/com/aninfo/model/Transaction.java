package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numeroDeTransacccion;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account cuenta;

    private Double monto;

    private String tipoDeTransaccion;


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

    public Transaction(){
    }

    public Account getCuenta() {
        return cuenta;
    }

    public void setCuenta(Account cuenta) {
        this.cuenta = cuenta;
    }
}
