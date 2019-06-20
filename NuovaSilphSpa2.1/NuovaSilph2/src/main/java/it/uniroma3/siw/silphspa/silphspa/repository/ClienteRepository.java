package it.uniroma3.siw.silphspa.silphspa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.silphspa.silphspa.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	//findByDatiDaRecuperare
	public List<Cliente> findByNome(String nome);
	public List<Cliente> findByNomeAndCognome(String nome, String cognome);

}
