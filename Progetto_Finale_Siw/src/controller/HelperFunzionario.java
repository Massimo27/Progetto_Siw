package controller;

import javax.servlet.http.HttpServletRequest;

public class HelperFunzionario {
	public boolean isValid(HttpServletRequest request) {
		//i campi sono tutti obbligatori
		boolean corretto = true;

		String id = request.getParameter("id").trim();
		String password = request.getParameter("password").trim();

		if(id.equals("")) {
			String mess ="Il campo id è obbligatorio!";
			request.setAttribute("idError", mess);
			corretto = false;
		}
		if(password.equals("")) {
			String mess ="Il campo password è obbligatorio!";
			request.setAttribute("passwordError", mess);
			corretto = false;
		}
		return corretto;
	}
}
