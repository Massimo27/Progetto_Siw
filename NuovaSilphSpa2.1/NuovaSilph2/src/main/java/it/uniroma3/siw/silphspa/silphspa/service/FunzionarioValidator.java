package it.uniroma3.siw.silphspa.silphspa.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import it.uniroma3.siw.silphspa.silphspa.model.Funzionario;

@Component
public class FunzionarioValidator implements org.springframework.validation.Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Funzionario.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
		
	}

}
