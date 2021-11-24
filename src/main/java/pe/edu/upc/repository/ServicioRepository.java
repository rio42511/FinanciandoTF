package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
	@Query("select count(s.nameServicio) from Servicio s where s.nameServicio =:name")
	public int buscarServicio(@Param("name") String nombreServicio);

	@Query("select s from Servicio s where s.nameServicio like %:name%")
	List<Servicio> findByName(String name);

	@Query("select s from Servicio s where s.nameServicio like %:name%")
	List<Servicio> findByNameLikeIgnoreCase(String name);
}
