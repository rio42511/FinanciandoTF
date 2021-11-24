package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Membership;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {
	@Query("select count(m.nameMembership) from Membership m where m.nameMembership =:name")
	public int buscarMembresia(@Param("name") String nombreMembresia);

	@Query("select m from Membership m where m.nameMembership like %:name%")
	 List<Membership> findByName(String name);

	@Query("select m from Membership m where m.nameMembership like %:name%")
	 List<Membership> findByNameLikeIgnoreCase(String name);

}
