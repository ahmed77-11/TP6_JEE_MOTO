package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Moto;
import util.JPAutil;

public class MotoDaoImp implements IMotoDao {
	private EntityManager entityManager = JPAutil.getEntityManager("TP5_MOTO");

	@Override
	public Moto save(Moto m) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(m);
		tx.commit();
		return m;
	}

	@Override
	public List<Moto> motosParMC(String mc) {
		List<Moto> motos = entityManager.createQuery("select m from Moto m where m.nomMoto like :mc").setParameter("mc", "%" + mc + "%").getResultList();

		return motos;
	}

	@Override
	public Moto getMoto(Long id) {
		return entityManager.find(Moto.class, id);
	}

	@Override
	public Moto updateMoto(Moto m) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(m);
		tx.commit();
		return m;
	}

	@Override
	public void deleteMoto(Long id) {
		Moto moto = entityManager.find(Moto.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(moto);
		entityManager.getTransaction().commit();
	}
}
