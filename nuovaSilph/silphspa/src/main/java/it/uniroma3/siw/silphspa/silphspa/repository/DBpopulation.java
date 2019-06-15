package it.uniroma3.siw.silphspa.silphspa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.silphspa.silphspa.model.Funzionario;


@Component
public class DBpopulation implements ApplicationRunner{
	
	@Autowired
	private FunzionarioRepository funzionarioRepository;
	
	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.deleteAll();
		this.addAll();
	}	
	
	private void deleteAll() {
		funzionarioRepository.deleteAll();
	}
	
	private void addAll() {
		
		Funzionario u1 = new Funzionario("mario","rossi","mr","mrpass","ADMIN");
		Funzionario u2 = new Funzionario("giuseppe","verdi","gi","gipass","GUEST");
		funzionarioRepository.save(u1);
		funzionarioRepository.save(u2);
	}

}
