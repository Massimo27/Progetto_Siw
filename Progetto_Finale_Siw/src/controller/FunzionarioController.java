package controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cliente;
import model.Funzionario;
import services.ClienteService;
import services.FunzionarioService;

@WebServlet("/funzionarioController")
public class FunzionarioController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//verifico con la richiesta di tipo post le credenziali del funzionario
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String nextPage="/logInFunzionario.jsp";

		HelperFunzionario helperFunzionario = new HelperFunzionario();
		HttpSession session = request.getSession();
		FunzionarioLogIn funzionarioLogIn = new FunzionarioLogIn();
		FunzionarioService funzionarioService = new FunzionarioService();

		if(helperFunzionario.isValid(request)) {
			Funzionario funzionario = funzionarioService.funzionarioById(request.getParameter("id"));
			if(funzionario==null) {

				String messa ="Il nome utente è errato!";
				request.setAttribute("idError", messa);
				nextPage = "/logInFunzionario.jsp";
			}
			else {
				if(funzionarioService.funzionarioByPwd(funzionario, request.getParameter("password"))) {
					nextPage="/funzionario.jsp";
					funzionarioLogIn.setNome(funzionario.getNome().toUpperCase());
					funzionarioLogIn.setCognome(funzionario.getCognome().toUpperCase());

					session.setAttribute("funzionarioLogIn", funzionarioLogIn);
				}
				else {
					String mess ="Il campo password è errato!";
					request.setAttribute("passwordError", mess);
					nextPage = "/logInFunzionario.jsp";
				}
			}
		}

		response.encodeUrl(nextPage);
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return;
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String nextPage = "/clienti.jsp";
		ClienteService clienteService = new ClienteService();
		List<Cliente> clienti = clienteService.listaClienti();
		request.setAttribute("clienti", clienti);
		
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
