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



}
