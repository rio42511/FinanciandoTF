package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	@Query("select count(ct.nameCategory) from Category ct where ct.nameCategory =:name")
	public int buscarCategoria(@Param("name") String nombreCategoria);

	@Query("select ct from Category ct where ct.nameCategory like %:name%")
	List<Category> findByName(String name);

	@Query("select ct from Category ct where ct.nameCategory like %:name%")
	List<Category> findByNameLikeIgnoreCase(String name);
}
