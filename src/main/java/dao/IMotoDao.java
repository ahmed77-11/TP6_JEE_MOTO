package dao;

import java.util.List;

import metier.entities.Moto;


public interface IMotoDao {
	public Moto save(Moto p);
	public List<Moto> motosParMC(String mc);
	public Moto getMoto(Long id);
	public Moto updateMoto(Moto m);
	public void deleteMoto(Long id);
}
