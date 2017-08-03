package com.Model;

public class fecha {
    String fechainicio, fechafin;

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }

    public fecha(String fechainicio, String fechafin) {
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
    }

    public fecha() {
    }
}
