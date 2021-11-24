package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "adviser")
public class Adviser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAsesor;

	@NotEmpty(message = "Ingrese nombre del Asesor")
	@Column(name = "nameAdviser", nullable = false, length = 50)
	private String nameAdviser;

	@NotEmpty(message = "Ingrese email del Asesor")
	@Email
	@Column(name = "emailAdviser", nullable = false, length = 50)
	private String emailAdviser;

	@NotEmpty(message = "Ingrese Telefono del Asesor")
	@Size(min = 9, max = 9)
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El telefono solo puede contener números")
	@Pattern(regexp = "[^a-z]+", message = "El telefono solo puede contener números")
	@Column(name = "phoneAdviser", nullable = false, length = 50)
	private String phoneAdviser;

	public Adviser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Adviser(int idAsesor, String nameAdviser, String emailAdviser, String phoneAdviser) {
		super();
		this.idAsesor = idAsesor;
		this.nameAdviser = nameAdviser;
		this.emailAdviser = emailAdviser;
		this.phoneAdviser = phoneAdviser;
	}

	public int getIdAsesor() {
		return idAsesor;
	}

	public void setIdAsesor(int idAsesor) {
		this.idAsesor = idAsesor;
	}

	public String getNameAdviser() {
		return nameAdviser;
	}

	public void setNameAdviser(String nameAdviser) {
		this.nameAdviser = nameAdviser;
	}

	public String getEmailAdviser() {
		return emailAdviser;
	}

	public void setEmailAdviser(String emailAdviser) {
		this.emailAdviser = emailAdviser;
	}

	public String getPhoneAdviser() {
		return phoneAdviser;
	}

	public void setPhoneAdviser(String phoneAdviser) {
		this.phoneAdviser = phoneAdviser;
	}

}
