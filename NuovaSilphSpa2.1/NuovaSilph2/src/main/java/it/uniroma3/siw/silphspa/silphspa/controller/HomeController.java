package it.uniroma3.siw.silphspa.silphspa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silphspa.silphspa.service.FotoService;

@Controller
public class HomeController  {

	@Autowired
	private FotoService fotoService;
	
	@RequestMapping("/")
	public String mostraTutte (Model model) {
		model.addAttribute("tutte", this.fotoService.tutte());
		return "home.html";
	}
	
	@RequestMapping(value = {"/home"}, method = RequestMethod.GET)
	public String welcome(Model model) {
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String role = details.getAuthorities().iterator().next().getAuthority();
		model.addAttribute("nickname", details.getUsername());
		model.addAttribute("role", role);
		return "home.html";
	}
	//@RequestMapping(value = "/listaClienti")
}

