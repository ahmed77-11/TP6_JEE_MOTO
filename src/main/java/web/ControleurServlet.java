package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;

import dao.IMotoDao;
import dao.MotoDaoImp;
import metier.entities.Moto;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {
	IMotoDao metier;

	@Override
	public void init() throws ServletException {
		metier = new MotoDaoImp();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/index.do")) {
			request.getRequestDispatcher("motos.jsp").forward(request, response);
		} else if (path.equals("/chercher.do")) {
			String motCle = request.getParameter("motCle");
			MotoModele model = new MotoModele();
			model.setMotCle(motCle);
			List<Moto> motos = metier.motosParMC(motCle);
			model.setMotos(motos);
			request.setAttribute("model", model);
			request.getRequestDispatcher("motos.jsp").forward(request, response);
		} else if (path.equals("/saisie.do")) {
			request.getRequestDispatcher("saisieMoto.jsp").forward(request, response);
		} else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
			String nom = request.getParameter("nom");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Moto m = metier.save(new Moto(nom, prix));
			request.setAttribute("motos", m);
			request.getRequestDispatcher("confirmation.jsp").forward(request, response);
		} else if (path.equals("/supprimer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			metier.deleteMoto(id);
			response.sendRedirect("chercher.do?motCle=");
		} else if (path.equals("/editer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Moto m = metier.getMoto(id);
			request.setAttribute("moto", m);
			request.getRequestDispatcher("editerMoto.jsp").forward(request, response);
		} else if (path.equals("/update.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nom");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Moto m = new Moto();
			m.setIdMoto(id);
			m.setNomMoto(nom);
			m.setPrix(prix);
			metier.updateMoto(m);
			request.setAttribute("moto", m);
			request.getRequestDispatcher("confirmation.jsp").forward(request, response);
		} else {
			response.sendError(Response.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
