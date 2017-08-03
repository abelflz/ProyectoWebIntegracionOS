package com.Model;

public class RegSolCheques {
    private int Id;
    private String Monto, ProveedorId;
    private String FechaRegistro;
    private String Estado, CuentaContableProveedor, CuentaContableRelCCBanco, ConceptoId;

    public RegSolCheques() {
    }

    public RegSolCheques(String ProveedorId, String ConceptoId, String Monto, String FechaRegistro, String Estado, String CuentaContableProveedor, String CuentaContableRelCCBanco) {
        this.ProveedorId = ProveedorId;
        this.ConceptoId = ConceptoId;
        this.Monto = Monto;
        this.FechaRegistro = FechaRegistro;
        this.Estado = Estado;
        this.CuentaContableProveedor = CuentaContableProveedor;
        this.CuentaContableRelCCBanco = CuentaContableRelCCBanco;
    }

    public RegSolCheques(int Id, String ProveedorId, String ConceptoId, String Monto, String FechaRegistro, String Estado, String CuentaContableProveedor, String CuentaContableRelCCBanco) {
        this.Id = Id;
        this.ProveedorId = ProveedorId;
        this.ConceptoId = ConceptoId;
        this.Monto = Monto;
        this.FechaRegistro = FechaRegistro;
        this.Estado = Estado;
        this.CuentaContableProveedor = CuentaContableProveedor;
        this.CuentaContableRelCCBanco = CuentaContableRelCCBanco;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getProveedorId() {
        return ProveedorId;
    }

    public void setProveedorId(String ProveedorId) {
        this.ProveedorId = ProveedorId;
    }

    public String getConceptoId() {
        return ConceptoId;
    }

    public void setConceptoId(String ConceptoId) {
        this.ConceptoId = ConceptoId;
    }

    public String getMonto() {
        return Monto;
    }

    public void setMonto(String Monto) {
        this.Monto = Monto;
    }

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(String FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getCuentaContableProveedor() {
        return CuentaContableProveedor;
    }

    public void setCuentaContableProveedor(String CuentaContableProveedor) {
        this.CuentaContableProveedor = CuentaContableProveedor;
    }

    public String getCuentaContableRelCCBanco() {
        return CuentaContableRelCCBanco;
    }

    public void setCuentaContableRelCCBanco(String CuentaContableRelCCBanco) {
        this.CuentaContableRelCCBanco = CuentaContableRelCCBanco;
    }

    
}