package it.uniroma3.siw.silphspa.silphspa.repository;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.silphspa.silphspa.model.Foto;
import it.uniroma3.siw.silphspa.silphspa.model.Funzionario;
import it.uniroma3.siw.silphspa.silphspa.service.FotoService;


@Component
public class DBpopulation implements ApplicationRunner{
	
	@Autowired
	private FunzionarioRepository funzionarioRepository;
	
	@Autowired
	private FotoService fotoService;
	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.deleteAll();
		this.addAll();
	}	
	
	private void deleteAll() {
		funzionarioRepository.deleteAll();
		for(Foto foto : this.fotoService.fotoAggiunte()) {
			foto.setAggiunta(null);
			fotoService.inserisci(foto);
		}
		
	}
	
	private void addAll() throws IOException, InterruptedException{
		
		System.out.println("Storing user...");
		
		Funzionario u1 = new Funzionario("mario","rossi","mr",null,"ADMIN");
		String funzionarioPassword = new BCryptPasswordEncoder().encode("mrpass");
		u1.setPassword(funzionarioPassword);
		
		//Funzionario u2 = new Funzionario("giuseppe","verdi","gi",null,"GUEST");
		
		funzionarioRepository.save(u1);
		//funzionarioRepository.save(u2);
		System.out.println("Done.\n");
	}

}
