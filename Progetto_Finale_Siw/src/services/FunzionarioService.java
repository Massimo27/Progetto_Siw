package services;

import java.util.ArrayList;
import java.util.List;

import model.Funzionario;

//il sistema implementa metodi di utilizzo getList
public class FunzionarioService {
	List<Funzionario> funzionari = new ArrayList<>();

	public FunzionarioService() {
		Funzionario f1 = new Funzionario();
		f1.setNome("Gabriele");
		f1.setCognome("Alecci");
		f1.setPassword("gabriele1997");
		f1.setId("510924");
		funzionari.add(f1);
	}

	public List<Funzionario> listaFunzionari(){
		return this.funzionari;
	}
	//verifica l'id
	public Funzionario funzionarioById(String id) {
		Funzionario funzionario = null;
		for(Funzionario f : funzionari) {
			if(f.getId().equals(id))
				funzionario = f;
		}
		return funzionario;
	}
	//verifica la psw
	public boolean funzionarioByPwd(Funzionario f, String pwd) {
		return f.getPassword().equals(pwd);	 
	}
}
