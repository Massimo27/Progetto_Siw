package it.uniroma3.siw.silphspa.silphspa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.silphspa.silphspa.model.Cliente;
import it.uniroma3.siw.silphspa.silphspa.repository.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente inserisci(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public List<Cliente> tutti(){
		return (List<Cliente>)clienteRepository.findAll();
	}
	
	public Cliente clientePerId(Long id) {
		return this.clienteRepository.findById(id).get();
	}
}
