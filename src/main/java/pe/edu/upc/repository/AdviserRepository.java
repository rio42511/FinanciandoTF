package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Adviser;

@Repository
public interface AdviserRepository extends JpaRepository<Adviser, Integer> {
	@Query("select count(a.nameAdviser) from Adviser a where a.nameAdviser =:name")
	public int buscarAsesor(@Param("name") String nombreAsesor);

	@Query("select a from Adviser a where a.nameAdviser like %:name%")
	List<Adviser> findByName(String name);

	@Query("select a from Adviser a where a.nameAdviser like %:name%")
	List<Adviser> findByNameLikeIgnoreCase(String name);

}
