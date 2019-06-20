package it.uniroma3.siw.silphspa.silphspa.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.silphspa.silphspa.model.Foto;

@Component
public class FotoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Foto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "autore", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "album", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzo", "required");
	}

}
