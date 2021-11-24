package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name ="bank")
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBank;
	
	@NotEmpty (message = "Ingrese nombre")
	@Column(name = "nameBank", nullable =false, length = 50)
	private String nameBank;

	@NotEmpty(message = "Ingrese Telefono de Banca por telefono")
	@Size(min = 7, max = 7)
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El Telefono solo puede contener números")
	@Pattern(regexp = "[^a-z]+", message = "El Telefono solo puede contener números")
	@Column(name ="phoneBank", nullable = false, length = 7)
	private String phoneBank;

	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bank(int idBank, String nameBank, String phoneBank) {
		super();
		this.idBank = idBank;
		this.nameBank = nameBank;
		this.phoneBank = phoneBank;
	}

	public int getIdBank() {
		return idBank;
	}

	public void setIdBank(int idBank) {
		this.idBank = idBank;
	}

	public String getNameBank() {
		return nameBank;
	}

	public void setNameBank(String nameBank) {
		this.nameBank = nameBank;
	}

	public String getPhoneBank() {
		return phoneBank;
	}

	public void setPhoneBank(String phoneBank) {
		this.phoneBank = phoneBank;
	}
	
	
}
