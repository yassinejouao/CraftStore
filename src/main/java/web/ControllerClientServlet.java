package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClientDaoImpl;
import dao.IClientDao;
import metier.entities.Client;
import metier.models.ClientModel;

@WebServlet(name = "ccs", urlPatterns = { "*.client" })
public class ControllerClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IClientDao clientDao;

	@Override
	public void init() throws ServletException {
		clientDao = new ClientDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		// index client page
		if (path.equals("/index.client")) {
			ClientModel clientModel = new ClientModel();
			clientModel.setKeyword("");
			List<Client> clients = clientDao.searchClientByKeyword("%%");
			clientModel.setClients(clients);
			req.setAttribute("clientModel", clientModel);
			req.getRequestDispatcher("clients.jsp").forward(req, resp);
		}

		// search for client
		else if (path.equals("/search.client")) {
			String keyword = req.getParameter("Keyword");
			ClientModel clientModel = new ClientModel();
			clientModel.setKeyword(keyword);
			List<Client> clients = clientDao.searchClientByKeyword("%" + keyword + "%");
			clientModel.setClients(clients);
			req.setAttribute("clientModel", clientModel);
			req.getRequestDispatcher("clients.jsp").forward(req, resp);
		}

		// add client page
		else if (path.equals("/add.client")) {
			req.getRequestDispatcher("addClient.jsp").forward(req, resp);
		}

		// save client and send confirmation page
		else if (path.equals("/save.client") && req.getMethod().equals("POST")) {
			String nameClient = req.getParameter("nameClient");
			String emailClient = req.getParameter("emailClient");
			Client c = clientDao.save(new Client(nameClient, emailClient));
			if (c == null) {
				req.getRequestDispatcher("clients.jsp").forward(req, resp);
			}
			req.setAttribute("message", "Client " + c.getNameClient() + " has been added successfully");
			req.getRequestDispatcher("confirmation.jsp").forward(req, resp);

		}
		// delete client and send confirmation page
		else if (path.equals("/delete.client")) {
			Long id = Long.parseLong(req.getParameter("id"));
			Boolean deleted = clientDao.deleteClient(id);
			if (deleted) {
				resp.sendRedirect("index.client");
			} else {
				req.setAttribute("message", "You can't delete this client");
				req.getRequestDispatcher("confirmation.jsp").forward(req, resp);
			}
		}
		// error page not found
		else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
