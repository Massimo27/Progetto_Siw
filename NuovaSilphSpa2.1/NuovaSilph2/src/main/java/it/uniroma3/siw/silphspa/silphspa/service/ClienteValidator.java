package it.uniroma3.siw.silphspa.silphspa.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import it.uniroma3.siw.silphspa.silphspa.model.Cliente;

@Component
public class ClienteValidator implements org.springframework.validation.Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Cliente.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
	}
	
}
