package com.Model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ValidarRSC implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return RegSolCheques.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegSolCheques rsc = (RegSolCheques) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "FechaRegistro", "required.FechaRegistro", "Debe de Insertar una fecha válida");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Estado", "required.Estado", "Debe de seleccionar el Estado");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "CuentaContableRelCCBanco", "required.CuentaContableRelCCBanco", "Debe de digitar la Cuenta Contable Relacionada a la cuenta corriente del Banco");
        if (rsc.getProveedorId().equals("-1")) {
            errors.reject("MustFill", "Debe de Seleccionar un Proveedor");
        }

        try {
            Double.parseDouble(rsc.getMonto());
        } catch (NumberFormatException e) {
            errors.reject("not a valid input", "Cuentas Contables y Monto deben de ser numéricos");
        }
        try {
            Long.parseLong(rsc.getCuentaContableRelCCBanco());
        } catch (NumberFormatException e) {
            errors.reject("not a valid input", "Cuentas Contables no deben de contener caractéres especiales\n"+e);
        }
    }
}
