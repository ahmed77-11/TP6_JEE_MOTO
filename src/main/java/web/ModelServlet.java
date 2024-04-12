package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import dao.ModelDaoImpl;
import dao.IModelDao;
import metier.entities.Model;

@WebServlet(name = "catServ", urlPatterns = { "/models", "/saisieModel", "/saveModel", "/supprimerModel",
		"/editerModel", "/updateModel" })
public class ModelServlet extends HttpServlet {
	IModelDao metier;

	@Override
	public void init() throws ServletException {
		metier = new ModelDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		System.out.println("PATH " + path);
		if (path.equals("/models")) {
			ModelModele mo = new ModelModele();
			List<Model> models = metier.getAllModels();
			mo.setModels(models);
			request.setAttribute("mo", mo);
			request.getRequestDispatcher("models.jsp").forward(request, response);
		} else if (path.equals("/saisieModel")) {
			request.getRequestDispatcher("saisieModel.jsp").forward(request, response);
		} else if (path.equals("/saveModel") && request.getMethod().equals("POST")) {
			Date dateMod = new Date();
			String nom = request.getParameter("nom");
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			try {
				dateMod = simpleDateFormat.parse(request.getParameter("dateMod"));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Model model = metier.save(new Model(nom, dateMod));
			request.setAttribute("model", model);
			response.sendRedirect("models");
		} else if (path.equals("/supprimerModel")) {
			Long id = Long.parseLong(request.getParameter("id"));
			metier.deleteModel(id);
			response.sendRedirect("models");
		} else if (path.equals("/editerModel")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Model model = metier.getModel(id);
			request.setAttribute("model", model);
			request.getRequestDispatcher("editerModel.jsp").forward(request, response);
		} else if (path.equals("/updateModel")) {
			Date dateCat = new Date();
			Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nom");
			Model model = new Model();
			model.setIdModel(id);
			model.setNomModel(nom);
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			try {
				dateCat = simpleDateFormat.parse(request.getParameter("dateCreation"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			model.setDateCreation(dateCat);
			metier.updateModel(model);
			response.sendRedirect("models");
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