package it.uniroma3.siw.silphspa.silphspa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.silphspa.silphspa.service.FotoService;

@Controller
public class HomeController  {

	@Autowired
	private FotoService fotoService;
	
	@RequestMapping("/")
	public String mostraTutte (Model model) {
		model.addAttribute("tutte",this.fotoService.tutte());
		return "home.html";
	}
	
}
