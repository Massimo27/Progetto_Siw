package it.uniroma3.siw.silphspa.silphspa.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silphspa.silphspa.model.Foto;
import it.uniroma3.siw.silphspa.silphspa.model.Funzionario;
import it.uniroma3.siw.silphspa.silphspa.service.ClienteService;
import it.uniroma3.siw.silphspa.silphspa.service.FotoService;
import it.uniroma3.siw.silphspa.silphspa.service.FunzionarioService;
import it.uniroma3.siw.silphspa.silphspa.service.FunzionarioValidator;

@Controller
public class FunzionarioController {
	@Autowired
	private FunzionarioService funzionarioService;
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private ClienteService clienteSevice;
	
	@Autowired
	private FunzionarioValidator funzionarioValidator;
	
	@RequestMapping(value="/funzionario", method = RequestMethod.POST)
	public String accessoFunzionario(@Valid @ModelAttribute("funzionario") Funzionario funzionario, Model model, BindingResult bindingResult) {
		this.funzionarioValidator.validate(funzionario, bindingResult);
		if(!bindingResult.hasErrors()) {
			if(this.funzionarioService.funzionarioPerNickname(funzionario.getUserName()).getPassword().equals(funzionario.getPassword()))
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
	
	@RequestMapping("/foto")
	public String salvaFoto(@Valid @ModelAttribute ("foto") Foto foto,Model model) {
		this.fotoService.inserisci(foto);
		model.addAttribute("foto",foto);
		return "Foto.html";
	}
	@RequestMapping("/inserisciFoto")
	public String inserisciFoto(Model model) {
		model.addAttribute("foto",new Foto());
		return "fotoForm.html";
	}
	

	@RequestMapping("/album")
	public String visualizza(@ModelAttribute("album") ArrayList<Foto> album) {
		for (Foto foto : album) {
			fotoService.inserisci(foto);
		}
		for(Foto f : album) {
			System.out.println(f.getId());
			}
		return "home.html";
	}
	
	@RequestMapping(value="/inserisciAlbum",params= {"addFoto"})
	public String aggiungiFoto(Model model,@ModelAttribute("album") List<Foto> album ) {
		Foto foto = new Foto();
		album.add(foto);
		model.addAttribute("album",album);
		return "albumForm.html";
	}
	
	@RequestMapping("/inserisciAlbum")
	public String inserisciAlbum(Model model) {
		List<Foto> album = new ArrayList<Foto>();
		Foto foto = new Foto();
		foto.setId((long ) 1);
		album.add(foto);
		for(Foto f : album) {
		System.out.println(f.getId());
		}
		model.addAttribute("album",album);
		return "albumForm.html";
	}
}