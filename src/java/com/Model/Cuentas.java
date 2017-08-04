package com.Model;

public class Cuentas {
   String CuentaId, Descripcion, TipoMovimiento;
   double Monto;

    public Cuentas() {
    }

    public Cuentas(String CuentaId, String Descripcion, String TipoMovimiento, double Monto) {
        this.CuentaId = CuentaId;
        this.Descripcion = Descripcion;
        this.TipoMovimiento = TipoMovimiento;
        this.Monto = Monto;
    }
    
    public Cuentas(String Descripcion, String TipoMovimiento, double Monto) {
        this.Descripcion = Descripcion;
        this.TipoMovimiento = TipoMovimiento;
        this.Monto = Monto;
    }

    public String getCuentaId() {
        return CuentaId;
    }

    public void setCuentaId(String CuentaId) {
        this.CuentaId = CuentaId;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getTipoMovimiento() {
        return TipoMovimiento;
    }

    public void setTipoMovimiento(String TipoMovimiento) {
        this.TipoMovimiento = TipoMovimiento;
    }

    public double getMonto() {
        return Monto;
    }

    public void setMonto(double Monto) {
        this.Monto = Monto;
    }
}
