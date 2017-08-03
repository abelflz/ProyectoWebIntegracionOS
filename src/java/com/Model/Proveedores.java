package com.Model;


public class Proveedores {
    private int Id;
    private String Nombre, TipoPersona, cedulaRNC, CuentaContable, Estado, Balance;

    public Proveedores() {
    }

    public Proveedores(String Nombre, String TipoPersona, String cedulaRNC, String Balance, String CuentaContable, String Estado) {
        this.Nombre = Nombre;
        this.TipoPersona = TipoPersona;
        this.cedulaRNC = cedulaRNC;
        this.Balance = Balance;
        this.CuentaContable = CuentaContable;
        this.Estado = Estado;
    }

    public Proveedores(int Id, String Nombre, String TipoPersona, String cedulaRNC, String Balance, String CuentaContable, String Estado) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.TipoPersona = TipoPersona;
        this.cedulaRNC = cedulaRNC;
        this.Balance = Balance;
        this.CuentaContable = CuentaContable;
        this.Estado = Estado;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTipoPersona() {
        return TipoPersona;
    }

    public void setTipoPersona(String TipoPersona) {
        this.TipoPersona = TipoPersona;
    }

    public String getCedulaRNC() {
        return cedulaRNC;
    }

    public void setCedulaRNC(String cedulaRNC) {
        this.cedulaRNC = cedulaRNC;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String Balance) {
        this.Balance = Balance;
    }

    public String getCuentaContable() {
        return CuentaContable;
    }

    public void setCuentaContable(String CuentaContable) {
        this.CuentaContable = CuentaContable;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
}
