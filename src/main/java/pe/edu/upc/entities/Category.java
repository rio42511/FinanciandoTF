package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategory;

	@NotEmpty(message = "Ingrese nombre de la Categoria")
	@Column(name = "nameCategory", nullable = false, length = 50)
	private String nameCategory;

	@NotEmpty(message = "Ingrese descripcion de la Categoria")
	@Column(name = "descriCategory", nullable = false, length = 50)
	private String descriCategory;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(int idCategory, String nameCategory, String descriCategory) {
		super();
		this.idCategory = idCategory;
		this.nameCategory = nameCategory;
		this.descriCategory = descriCategory;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public String getDescriCategory() {
		return descriCategory;
	}

	public void setDescriCategory(String descriCategory) {
		this.descriCategory = descriCategory;
	}
	
	
	
}
