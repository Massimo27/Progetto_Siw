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

import it.uniroma3.siw.silphspa.silphspa.model.Cliente;
import it.uniroma3.siw.silphspa.silphspa.model.Foto;
import it.uniroma3.siw.silphspa.silphspa.service.ClienteService;
import it.uniroma3.siw.silphspa.silphspa.service.ClienteValidator;
import it.uniroma3.siw.silphspa.silphspa.service.FotoService;
import it.uniroma3.siw.silphspa.silphspa.service.FotoValidator;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteValidator clienteValidator;
	@Autowired
	private FotoValidator fotoValidator;
	@Autowired
	private FotoService fotoService;
	@RequestMapping( value = "/cliente", method = RequestMethod.POST)
	public String newCliente(@Valid @ModelAttribute("cliente") Cliente cliente, Model model, BindingResult bindingResult) {
		
		this.clienteValidator.validate(cliente, bindingResult);
		if(!bindingResult.hasErrors()) {
			//??
			this.clienteService.inserisci(cliente);
			for(Foto foto : this.fotoService.fotoAggiunte()) {
				foto.setAggiunta(null);
				fotoService.inserisci(foto);
			}
			model.addAttribute("clienti", this.clienteService.tutti());
			return "richiestaEffettuata.html";
		}
		else
			return "clienteForm.html";
	}
	//metodo che permette di rilanciare l'applicazione
		@RequestMapping("/addCliente")
		public String addCliente(Model model) {
			Cliente cliente= new Cliente();
			for(Foto foto : this.fotoService.fotoAggiunte()) {
				if(cliente.getCarrello()!=null)
				cliente.setCarrello(cliente.getCarrello()+foto.getIndirizzo()+"; ");
				else 
					cliente.setCarrello(foto.getIndirizzo()+"; ");
			}
			model.addAttribute("cliente", cliente);
			model.addAttribute("aggiunte",this.fotoService.fotoAggiunte());
			return "clienteForm.html";
		}
		
	//-----------------------------METODI DI RICERCA ------------------------------------------//
		@RequestMapping(value = "/ricercaFotografo", method= RequestMethod.POST)
		public String ricercaFotografo(@ModelAttribute("ricerca") Foto foto, Model model,BindingResult bindingResult) {
			fotoValidator.validate(foto, bindingResult);
			if(!bindingResult.hasFieldErrors("autore")) {
			model.addAttribute("tutte", this.fotoService.fotoPerAutore(foto.getAutore()));
			return "home.html";
			}
			else 
			return "ricerca.html";
		}
		
		@RequestMapping(value = "/ricercaAlbum", method= RequestMethod.POST)
		public String ricercaAlbum(@ModelAttribute("ricerca") Foto foto, Model model,BindingResult bindingResult) {
			fotoValidator.validate(foto, bindingResult);
			if(!bindingResult.hasFieldErrors("album")) {
			model.addAttribute("tutte", this.fotoService.fotoPerAlbum(foto.getAlbum()));
			return "home.html";
			}
			else 
			return "ricerca.html";
		}
		
		@RequestMapping("/cercaFoto")
		public String ricercaFoto(Model model) {
			// To_do
			Foto foto = new Foto();
			model.addAttribute("ricerca", foto);
			return "ricerca.html";
		}
		
		@RequestMapping(value="/foto/{id}", method = RequestMethod.GET)
		public String acquistaFoto(@PathVariable("id") Long id, Model model) {
			if(id!=null) {
				Foto foto = this.fotoService.fotoPerId(id);
				if(foto.getAggiunta()==null)
				foto.setAggiunta(true);
				else 
					foto.setAggiunta(null);
				this.fotoService.inserisci(foto);
				model.addAttribute("tutte",this.fotoService.tutte());
				return "redirect:/";
			}
			else 
				return "redirect:/";
		}
		
		@RequestMapping(value="remove/foto/{id}", method = RequestMethod.GET)
		public String rimuoviFoto(@PathVariable("id") Long id, Model model) {
			if(id!=null) {
				Foto foto = this.fotoService.fotoPerId(id);
				if(foto.getAggiunta()==null)
				foto.setAggiunta(true);
				else 
					foto.setAggiunta(null);
				this.fotoService.inserisci(foto);
				model.addAttribute("aggiunte",this.fotoService.fotoAggiunte());
				return "redirect:/addCliente";
			}
			else 
				return "redirect:/addCliente";
		}
		
		
	
		
}
