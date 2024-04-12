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
import dao.IModelDao;
import dao.ModelDaoImpl;
import metier.entities.Model;
import metier.entities.Moto;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {
	IMotoDao metier;
	IModelDao metierMod;

	@Override
	public void init() throws ServletException {
		metier = new MotoDaoImp();
		metierMod=new ModelDaoImpl();
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
			List<Model> models = metierMod.getAllModels();
			ModelModele mo= new ModelModele();
			mo.setModels(models);
			request.setAttribute("moModele", mo);

			request.getRequestDispatcher("saisieMoto.jsp").forward(request, response);
		} else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
			String nom = request.getParameter("nom");
			Long modelId=Long.parseLong(request.getParameter("model"));
			System.out.print(modelId);
			double prix = Double.parseDouble(request.getParameter("prix"));
			Model mode=metierMod.getModel(modelId);
			/*
			 * Moto mot = new Moto(); mot.setNomMoto(nom); mot.setPrix(prix);
			 * mot.setModel(mode);
			 */
			Moto m = metier.save(new Moto(nom,prix,mode));
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
			List<Model> models = metierMod.getAllModels();
			ModelModele mo= new ModelModele();
			mo.setModels(models);
			request.setAttribute("moModele", mo);
			request.getRequestDispatcher("editerMoto.jsp").forward(request, response);
		} else if (path.equals("/update.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nom");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Long modelId=Long.parseLong(request.getParameter("model"));
			Moto m = new Moto();
			m.setIdMoto(id);
			m.setNomMoto(nom);
			m.setPrix(prix);
			Model mode=metierMod.getModel(modelId);
			m.setModel(mode);
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
