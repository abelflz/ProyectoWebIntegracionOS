package com.Model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class ValidarConceptosPago implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return ConceptoPagos.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ConceptoPagos cp = (ConceptoPagos)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Descripcion", "required.descripcion", "El campo Descripción es Requerido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Estado", "required.estado", "El campo Estado es Requerido");
    }
    
}
