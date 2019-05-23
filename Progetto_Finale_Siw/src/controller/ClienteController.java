package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.ClienteService;

@WebServlet("/utenteController")
public class ClienteController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextPage = "";
		HelperCliente helperCliente = new HelperCliente();
		HttpSession session = request.getSession();
		ClienteForm clienteForm = new ClienteForm();
		ClienteService clienteService = new ClienteService();
		
		if(helperCliente.isValid(request)) {
			nextPage="/fineAcquisto.jsp";

			//per mantenere i parametri in piu richieste li aggiungo in una sessione e non nella richiesta
			//modifico request -> session
			clienteForm.setNome(request.getParameter("nome").toUpperCase());	
			clienteForm.setCognome(request.getParameter("cognome").toUpperCase());
			clienteForm.setEmail(request.getParameter("email").toUpperCase());
			
			session.setAttribute("clienteForm", clienteForm);
			//aggiungo alla lista->provvisorio
			clienteService.listaClienti().add(clienteForm);
			
		}
		else {
			nextPage="/formUtente.jsp";
		}
		
		
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return;
	}
}
