/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author abelf
 */
public class ValidarProveedores implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return Proveedores.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Proveedores p = (Proveedores) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Nombre", "required.Nombre", "El campo Nombre y Apellido es Requerido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "TipoPersona", "required.TipoPersona", "El campo Tipo de Persona es Requerido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cedulaRNC", "required.cedulaRNC", "El campo Cédula / RNC es Requerido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "CuentaContable", "required.CuentaContable", "El campo Cuenta Contable es Requerido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Estado", "required.estado", "El campo Estado es Requerido");

        try {
            Long.parseLong(p.getCuentaContable());
        } catch (NumberFormatException e) {
            errors.reject("InvalidType", "Debe de digitar caracteres numéricos en la Cuenta Contable");
        }
        try {
            Double.parseDouble(p.getBalance());
        } catch (NumberFormatException e) {
            errors.reject("InvalidType", "Debe de digitar caracteres numéricos en el Balance");
        }

        try {
            Long.parseLong(p.getCedulaRNC());
        } catch (NumberFormatException e) {
            errors.reject("InvalidInput", "Cédula no válida. Debe digitar únicamente valores numéricos");
        }
        if (VerificaCedula(p.getCedulaRNC()) == 1) {
            errors.reject("InvalidInput", "Cédula no válida. Cédula/RNC debe de tener longitud = 11, no puede ser 00000000000");
        }
        if (VerificaCedula(p.getCedulaRNC()) == 2) {
            errors.reject("InvalidInput", "Cédula no válida");
        }
    }

    @SuppressWarnings({"FinallyDiscardsException", "UseSpecificCatch"})
    public static int VerificaCedula(String cedula) {
        int vnTotal = 0;
        int[] digitoMult = new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1};
        int vCedlength = cedula.trim().length();

        if (vCedlength < 11 || vCedlength > 11 || cedula.equals("00000000000")) {
            return 1;
        }
        for (int vDig = 1; vDig <= vCedlength; vDig++) {
            int vCalculo = Integer.parseInt(cedula.substring(vDig - 1, vDig)) * digitoMult[vDig - 1];
            if (vCalculo < 10) {
                vnTotal += vCalculo;
            } else {
                vnTotal += Integer.parseInt(String.valueOf(vCalculo).substring(0, 1)) + Integer.parseInt(String.valueOf(vCalculo).substring(1, 2));
            }
        }
        if (vnTotal % 10 == 0) {
            return 0;
        } else {
            return 2;
        }
    }
}
