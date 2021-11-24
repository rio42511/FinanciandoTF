package pe.edu.upc.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "advisory")
public class Advisory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAdvisory;
	
	@Column(name= "codeAdvisory", nullable =false, length = 4)
	private String codeAdvisory;
	
	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	private Users users;
	
	@ManyToOne
	@JoinColumn(name = "idAsesor", nullable =false)
	private Adviser adviser;
	
	@ManyToOne
	@JoinColumn(name = "idServicio", nullable = false)
	private Servicio servicio;
	
	@NotNull(message = "Ingrese fecha de la asesoría")
	@Future(message = "La fecha debe ser posterior")
	@Temporal(TemporalType.DATE)
	@Column(name = "dateAdvisory")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateAdvisory;

	public Advisory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Advisory(int idAdvisory, String codeAdvisory, Users users, Adviser adviser, Servicio servicio,
			Date dateAdvisory) {
		super();
		this.idAdvisory = idAdvisory;
		this.codeAdvisory = codeAdvisory;
		this.users = users;
		this.adviser = adviser;
		this.servicio = servicio;
		this.dateAdvisory = dateAdvisory;
	}

	public int getIdAdvisory() {
		return idAdvisory;
	}

	public void setIdAdvisory(int idAdvisory) {
		this.idAdvisory = idAdvisory;
	}

	public String getCodeAdvisory() {
		return codeAdvisory;
	}

	public void setCodeAdvisory(String codeAdvisory) {
		this.codeAdvisory = codeAdvisory;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Adviser getAdviser() {
		return adviser;
	}

	public void setAdviser(Adviser adviser) {
		this.adviser = adviser;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Date getDateAdvisory() {
		return dateAdvisory;
	}

	public void setDateAdvisory(Date dateAdvisory) {
		this.dateAdvisory = dateAdvisory;
	}
		
}
