package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "membership")
public class Membership {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMembership;

	@NotEmpty(message = "Ingrese nombre de la Membresia")
	@Column(name = "nameMembership", nullable = false, length = 50)
	private String nameMembership;

	@NotEmpty(message = "Ingrese descripcion de la Membresia")
	@Column(name = "descripMembership", nullable = false, length = 50)
	private String descripMembership;

	@NotEmpty(message = "Ingrese el precio de la Membresia")
	@Positive(message = "El monto debe de ser positivo")
	@Digits(integer = 2, fraction = 2)
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El monto solo puede contener números")
	@Pattern(regexp = "[^a-z]+", message = "El monto solo puede contener números")
	@Column(name = "priceMembership", nullable = false, length = 50)
	private String priceMembership;

	public Membership() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Membership(int idMembership, String nameMembership, String descripMembership, String priceMembership) {
		super();
		this.idMembership = idMembership;
		this.nameMembership = nameMembership;
		this.descripMembership = descripMembership;
		this.priceMembership = priceMembership;
	}

	public int getIdMembership() {
		return idMembership;
	}

	public void setIdMembership(int idMembership) {
		this.idMembership = idMembership;
	}

	public String getNameMembership() {
		return nameMembership;
	}

	public void setNameMembership(String nameMembership) {
		this.nameMembership = nameMembership;
	}

	public String getDescripMembership() {
		return descripMembership;
	}

	public void setDescripMembership(String descripMembership) {
		this.descripMembership = descripMembership;
	}

	public String getPriceMembership() {
		return priceMembership;
	}

	public void setPriceMembership(String priceMembership) {
		this.priceMembership = priceMembership;
	}

}
