package com.example.myapplication.model;

public class CuentaBancaria {


    private String cbu;
    private String alias;


    public CuentaBancaria(String cbu, String alias) {
        this.cbu = cbu;
        this.alias = alias;
    }

    public CuentaBancaria( ) {
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCbu() {
        return cbu;
    }

    public String getAlias() {
        return alias;
    }
}