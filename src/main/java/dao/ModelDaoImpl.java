package dao;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Model;
import util.JPAutil;

public class ModelDaoImpl implements IModelDao {
// TP6_JEE à replacer par votre persistence unit, consultez votre
//fichier persistence.xml <persistence-unit name="TP6_JEE">
	private EntityManager entityManager = JPAutil.getEntityManager("TP5_MOTO");

	@Override
	public Model save(Model model) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(model);
		tx.commit();
		return model;
	}

	@Override
	public Model getModel(Long id) {
		return entityManager.find(Model.class, id);
	}

	@Override
	public Model updateModel(Model model) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(model);
		tx.commit();
		return model;
	}

	@Override
	public void deleteModel(Long id) {
		Model categorie = entityManager.find(Model.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(categorie);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Model> getAllModels() {
		List<Model> models = entityManager.createQuery("select m from Model m").getResultList();
		return models;
	}

}