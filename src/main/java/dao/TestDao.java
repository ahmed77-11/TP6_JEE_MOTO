package dao;

import java.util.List;
import metier.entities.Moto;

public class TestDao {
	public static void main(String[] args) {
		MotoDaoImp mdao = new MotoDaoImp();
		Moto moto = mdao.save(new Moto("FORZA", 2800));
		System.out.println(moto);
		List<Moto> motos = mdao.motosParMC("HP");
		for (Moto m : motos)
			System.out.println(m);
	}
}
