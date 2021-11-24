package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "servicio")
public class Servicio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idServicio;
	
	@NotEmpty (message = "Ingrese nombre del Servicio")
	@Column(name = "nameServicio", nullable =false, length = 50)
	private String nameServicio;
	
	@NotEmpty(message = "Ingrese Codigo del Servicio")
	@Column(name = "codeServicio", nullable =false, length = 4)
	private String codeServicio;
	
	@NotEmpty(message = "Ingrese descripcion del Servicio")
	@Column(name = "descriServicio", nullable = false, length = 50)
	private String descriServicio;
	
	@ManyToOne
	@JoinColumn(name = "idCategory", nullable = false)
	private Category category;

	public Servicio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Servicio(int idServicio, String nameServicio, String codeServicio, String descriServicio, Category category) {
		super();
		this.idServicio = idServicio;
		this.nameServicio = nameServicio;
		this.codeServicio = codeServicio;
		this.descriServicio = descriServicio;
		this.category = category;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public String getNameServicio() {
		return nameServicio;
	}

	public void setNameServicio(String nameServicio) {
		this.nameServicio = nameServicio;
	}

	public String getCodeServicio() {
		return codeServicio;
	}

	public void setCodeServicio(String codeServicio) {
		this.codeServicio = codeServicio;
	}

	public String getDescriServicio() {
		return descriServicio;
	}

	public void setDescriServicio(String descriServicio) {
		this.descriServicio = descriServicio;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
		
}
