package controller;

import javax.servlet.http.HttpServletRequest;

public class HelperCliente {
	public boolean isValid(HttpServletRequest request) {
		//se stringa vuota: l'utente deve reinserire i dati
		boolean corretto =true;
		
		String nome = request.getParameter("nome").trim();
		String cognome = request.getParameter("cognome").trim();
		String email = request.getParameter("email").trim();
		
		if(nome.equals("")) {
			String mess = "Il campo Nome è obbligatorio!";
			request.setAttribute("nomeError", mess);
			corretto=false;
		}
		
		if(cognome.equals("")) {
			String mess = "Il campo Cognome è obbligatorio!";
			request.setAttribute("cognomeError", mess);
			corretto=false;
		}
		
		if(email.equals("")) {
			String mess = "Il campo Matricola è obbligatorio!";
			request.setAttribute("emailError", mess);
			corretto=false;
		}
		return corretto;
	}
}
