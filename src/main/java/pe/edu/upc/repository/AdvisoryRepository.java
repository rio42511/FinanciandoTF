package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Advisory;

@Repository
public interface AdvisoryRepository extends JpaRepository<Advisory, Integer>{
	
	@Query("select count(a.codeAdvisory) from Advisory a where a.codeAdvisory =:code")
	public int buscarAsesoria(@Param("code") String codigoAsesoria);

	@Query("select a from Advisory a where a.codeAdvisory like %:code%")
	List<Advisory> findByCode(String code);

	@Query("select a from Advisory a where a.codeAdvisory like %:code%")
	List<Advisory> findByCodeLikeIgnoreCase(String code);


	@Query(value = "SELECT count(ad.id_asesor), ad.name_adviser from advisory a join adviser ad on a.id_asesor= ad.id_asesor group by (ad.name_adviser) order by count(a.id_asesor) desc limit 1", nativeQuery= true)
	public List<String[]> asesorTop();
	
	@Query(value = "SELECT count(se.id_servicio), se.name_servicio from advisory a join servicio se on a.id_servicio= se.id_servicio group by (se.name_servicio) order by count(a.id_servicio) desc limit 1", nativeQuery= true)
	public List<String[]> servicioTop();

	@Query(value = "SELECT count(us.id), us.name from advisory a join users us on a.id= us.id group by (us.name) order by count(a.id) desc limit 1", nativeQuery= true)
	public List<String[]> usuarioTop();

	//Ronaldo Caballero
	@Query(value = "SELECT count(a.id), us.name, se.name_servicio from advisory a join users us on a.id= us.id join servicio se on se.id_servicio=a.id_servicio group by (us.name,se.name_servicio) order by count(a.id) desc", nativeQuery= true)
	public List<String[]> usuariosTopxServicio();
	
	@Query("SELECT us.name, se.name_servicio from advisory a join users us on a.id= us.id join servicio se on se.id_servicio=a.id_servicio where se.name_servicio like %:name_service%")
	public List<String[]> usuariosXservicio(String name_service);
	
}
