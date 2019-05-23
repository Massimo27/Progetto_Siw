package services;

import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteService {
	List<Cliente> clienti = new ArrayList<>();
	
	public ClienteService() {
		Cliente c1 = new Cliente();
		c1.setNome("Mario");
		c1.setCognome("Rossi");
		c1.setEmail("mario.rossi@gmail.com");
		clienti.add(c1);
	}
	
	public List<Cliente> listaClienti(){
		return this.clienti;
	}
}
