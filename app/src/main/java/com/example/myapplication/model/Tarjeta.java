package com.example.myapplication.model;

import java.util.Date;

public class Tarjeta {

    private String numero;
    private String ccv;
    private Date vencimiento;
    private boolean esCredito;

    public Tarjeta() {
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public void setEsCredito(boolean esCredito) {
        this.esCredito = esCredito;
    }

    public Tarjeta(String numero, String ccv, Date vencimiento, boolean esCredito) {
        this.numero = numero;
        this.ccv = ccv;
        this.vencimiento = vencimiento;
        this.esCredito = esCredito;
    }

    public String getNumero() {
        return numero;
    }

    public String getCcv() {
        return ccv;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public boolean isEsCredito() {
        return esCredito;
    }
}
