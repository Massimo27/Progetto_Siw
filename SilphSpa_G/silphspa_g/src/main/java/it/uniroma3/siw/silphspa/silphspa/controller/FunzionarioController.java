package it.uniroma3.siw.silphspa.silphspa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silphspa.silphspa.model.Funzionario;
import it.uniroma3.siw.silphspa.silphspa.service.ClienteService;
import it.uniroma3.siw.silphspa.silphspa.service.FunzionarioService;
import it.uniroma3.siw.silphspa.silphspa.service.FunzionarioValidator;

@Controller
public class FunzionarioController {
	@Autowired
	private FunzionarioService funzionarioService;
	
	@Autowired
	private ClienteService clienteSevice;
	
	@Autowired
	private FunzionarioValidator funzionarioValidator;
	
	@RequestMapping(value="/funzionario", method = RequestMethod.POST)
	public String accessoFunzionario(@Valid @ModelAttribute("funzionario") Funzionario funzionario, Model model, BindingResult bindingResult) {
		this.funzionarioValidator.validate(funzionario, bindingResult);
		if(!bindingResult.hasErrors()) {
			if(this.funzionarioService.funzionarioPerNickname(funzionario.getNickname()).getPassword().equals(funzionario.getPassword()))
				model.addAttribute("funzionario", funzionario);
			return "funzionario.html";
		}
		else
			return "logInFunzionario.html";
	}
	
	//metodo che permette di rilanciare l'applicazione
	@RequestMapping("/logInFunzionario")
	public String logInFunzionario(Model model) {
		//bisogna prende un utente gia registrato e non crearne uno nuovo...
		model.addAttribute("funzionario", new Funzionario());
		return "logInFunzionario.html";
	}
	
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
	public String getCliente(@PathVariable("id") Long id, Model model) {
		if(id!=null) {
			model.addAttribute("cliente", this.clienteSevice.clientePerId(id));
			return "cliente.html";
		}
		else {
			model.addAttribute("clienti", this.clienteSevice.tutti());
			return "clienti.html";
		}
	}
	@RequestMapping("/listaClienti")
	public String listaClienti(Model model) {
		//bisogna prende un utente gia registrato e non crearne uno nuovo...
		model.addAttribute("clienti", clienteSevice.tutti());
		return "clienti.html";
	}
}