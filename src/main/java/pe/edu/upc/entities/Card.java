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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "card")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCard;
	
	@NotEmpty(message = "Ingrese numero de tarjeta")
	@Size(min = 16, max= 16)
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El numero de la tarjeta solo puede contener números")
	@Pattern(regexp = "[^a-z]+", message = "El numero de la tarjeta solo puede contener números")
	@Column(name = "numberCard", nullable = false, length =16)
	private String numberCard;
	
	@NotNull(message = "Ingrese fecha de expiracion")
	@Future(message = "La fecha debe ser posterior")
	@Temporal(TemporalType.DATE)
	@Column(name = "expireddateCard")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expireddateCard;
	
	@NotEmpty(message = "Ingrese nombre del titular")
	@Column(name = "namepropertyCard", nullable = false, length = 50)
	private String namepropertyCard;
	
	@NotEmpty(message = "Ingrese CVV de la tarjeta")
	@Size(min = 3, max= 3)
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El CVV solo puede contener números")
	@Pattern(regexp = "[^a-z]+", message = "El CVV solo puede contener números")
	@Column(name = "cvvCard", nullable = false, length =3)
	private String cvvCard;
	
	@ManyToOne
	@JoinColumn(name = "idBank", nullable =false)
	private Bank bank;

	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Card(int idCard, String numberCard,Date expireddateCard, String namepropertyCard,
			 String cvvCard, Bank bank) {
		super();
		this.idCard = idCard;
		this.numberCard = numberCard;
		this.expireddateCard = expireddateCard;
		this.namepropertyCard = namepropertyCard;
		this.cvvCard = cvvCard;
		this.bank = bank;
	}

	public int getIdCard() {
		return idCard;
	}

	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}

	public String getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}

	public Date getExpireddateCard() {
		return expireddateCard;
	}

	public void setExpireddateCard(Date expireddateCard) {
		this.expireddateCard = expireddateCard;
	}

	public String getNamepropertyCard() {
		return namepropertyCard;
	}

	public void setNamepropertyCard(String namepropertyCard) {
		this.namepropertyCard = namepropertyCard;
	}

	public String getCvvCard() {
		return cvvCard;
	}

	public void setCvvCard(String cvvCard) {
		this.cvvCard = cvvCard;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	
	
	
}
