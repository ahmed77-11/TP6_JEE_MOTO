package metier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Model implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idModel;
	private String nomModel;
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	@OneToMany(mappedBy = "model")
	private List<Moto> motos;

	public Model() {
		super();
	}

	public Model(String nomModel, Date dateCreation) {
		super();
		this.nomModel = nomModel;
		this.dateCreation = dateCreation;
	}

	public Long getIdModel() {
		return idModel;
	}

	public void setIdModel(Long idModel) {
		this.idModel = idModel;
	}

	public String getNomModel() {
		return nomModel;
	}

	public void setNomModel(String nomModel) {
		this.nomModel = nomModel;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public List<Moto> getMotos() {
		return motos;
	}

	public void setMotos(List<Moto> motos) {
		this.motos = motos;
	}

}