package it.uniroma3.siw.silphspa.silphspa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silphspa.silphspa.model.Cliente;
import it.uniroma3.siw.silphspa.silphspa.service.ClienteService;
import it.uniroma3.siw.silphspa.silphspa.service.ClienteValidator;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteValidator clienteValidator;
	@RequestMapping( value = "/cliente", method = RequestMethod.POST)
	public String newCliente(@Valid @ModelAttribute("cliente") Cliente cliente, Model model, BindingResult bindingResult) {
		
		this.clienteValidator.validate(cliente, bindingResult);
		if(!bindingResult.hasErrors()) {
			//??
			this.clienteService.inserisci(cliente);
			model.addAttribute("clienti", this.clienteService.tutti());
			return "richiestaEffettuata.html";
		}
		else
			return "clienteForm.html";
	}
	//metodo che permette di rilanciare l'applicazione
		@RequestMapping("/addCliente")
		public String addCliente(Model model) {
			model.addAttribute("cliente", new Cliente());
			return "clienteForm.html";
		}
}
