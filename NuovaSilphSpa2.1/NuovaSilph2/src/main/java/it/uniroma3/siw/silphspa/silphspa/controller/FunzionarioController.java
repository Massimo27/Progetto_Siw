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
import it.uniroma3.siw.silphspa.silphspa.model.Foto;
import it.uniroma3.siw.silphspa.silphspa.model.Funzionario;
import it.uniroma3.siw.silphspa.silphspa.service.ClienteService;
import it.uniroma3.siw.silphspa.silphspa.service.FotoService;
import it.uniroma3.siw.silphspa.silphspa.service.FotoValidator;
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
	private FotoValidator fotoValidator;
	
	@Autowired
	private FunzionarioValidator funzionarioValidator;
	

	@RequestMapping("/cliente/funzionario")
	public String reindirizza1() {
		return "redirect:/funzionario";
	}
	@RequestMapping("/cliente/inserisciNomeAlbum")
	public String reindirizza2() {
		return "redirect:/inserisciNomeAlbum";
	}
	@RequestMapping("/cliente/inserisciFoto")
	public String reindirizza3() {
		return "redirect:/inserisciFoto";
	}
	
	@RequestMapping(value= {"/funzionario"}, method = RequestMethod.GET)
	public String accessoFunzionario(@Valid @ModelAttribute("funzionario") Funzionario funzionario, Model model, BindingResult bindingResult) {
		/*this.funzionarioValidator.validate(funzionario, bindingResult);
		if(!bindingResult.hasErrors()) {
			if(this.funzionarioService.funzionarioPerUser_name(funzionario.getNickname()).getPassword().equals(funzionario.getPassword()))*/
				model.addAttribute("funzionario", funzionario);
				model.addAttribute("clienti", this.clienteSevice.tutti());
			return "funzionario.html";
		}
		/*else
			return "logInFunzionario.html";
	}*/
	
	//metodo che permette di rilanciare l'applicazione
	@RequestMapping("/logInFunzionario")
	public String logInFunzionario(Model model) {
		//bisogna prende un utente gia registrato e non crearne uno nuovo...
		model.addAttribute("funzionario", new Funzionario());
		return "logInFunzionario.html";
	}
	
	//LISTA CLIENTI CON LINK PER OGNI CLIENTE
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
	}//LISTA CLIENTI NELLA PAGINA FUNZIONARIO
	@RequestMapping("/listaClienti")
	public String listaClienti(Model model) {
		//bisogna prende un utente gia registrato e non crearne uno nuovo...
		model.addAttribute("clienti", clienteSevice.tutti());
		return "clienti.html";
	}
	
	//SALVA LA FOTO NEL DATABASE CON I DATI DEL FORM
	@RequestMapping("/foto")
	public String salvaFoto(@Valid @ModelAttribute ("foto") Foto foto,Model model,BindingResult bindingResult) {
		fotoValidator.validate(foto, bindingResult);
		if(bindingResult.hasFieldErrors("autore")||bindingResult.hasFieldErrors("indirizzo"))
			return "fotoForm.html";
		else 
		this.fotoService.inserisci(foto);
		model.addAttribute("foto",foto);
		return "Foto.html";
	}//PORTA ALLA PAGINA DI INSERIMENTO FOTO
	@RequestMapping({"/inserisciFoto"})
	public String inserisciFoto(Model model) {
		model.addAttribute("foto",new Foto());
		return "fotoForm.html";
	}
	//SALVA L'ALBUM CON TUTTE LE FOTO ALL'INTERNO NEL DATABASE
	@RequestMapping("/album")
	public String visualizza( @ModelAttribute("foto") Foto photo, Model model,BindingResult bindingResult) {
			fotoValidator.validate(photo, bindingResult);
			if(bindingResult.hasErrors())
				return "albumForm.html";
			else 
			fotoService.inserisci(photo);
			model.addAttribute("tutte",fotoService.tutte()); 	 	
		return "home.html";
	}
	//UNA VOLTA PREMUTO IL TASTO AGGIUNGE UNA FOTO DA COMPILARE NELL'ALBUM
	@RequestMapping(value="/album",params= {"addFoto"})
	public String aggiungiFoto(Model model, @ModelAttribute("foto") Foto foto ,BindingResult bindingResult) {
		fotoValidator.validate(foto, bindingResult);
		if(bindingResult.hasErrors())
			return "albumForm.html";
		else
		this.fotoService.inserisci(foto);
		model.addAttribute("foto", foto);
		return "albumForm.html";
		
	}
	@RequestMapping("/inserisciAlbum")
	public String insersciAlbum(Model model,@ModelAttribute("foto") Foto foto) {
		Foto photo = new Foto();
		photo.setAlbum(foto.getAlbum());
		model.addAttribute("foto",photo);
		return "albumForm.html";
	}
	
	//PORTA ALLA PAGINA DI INSERIMENTO NOME ALBUM
	@RequestMapping({"/inserisciNomeAlbum"})
	public String inserisciNomeAlbum(Model model) {
		model.addAttribute("foto",new Foto());
		return "nomeAlbum.html";
	}
}