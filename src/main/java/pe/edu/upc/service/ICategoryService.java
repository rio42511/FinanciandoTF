package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Category;

public interface ICategoryService {

	public void insert(Category ct);

	List<Category> list();

	public void delete(int id);

	Optional<Category> listarId(int idCategory);

	List<Category> findByName(String name);

	List<Category> findByNameLikeIgnoreCase(String name);

}
