package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
	@Query("select count(c.numberCard) from Card c where c.numberCard =:name")
	public int buscarTarjeta(@Param("name") String numeroTarjeta);

	@Query("select c from Card c where c.numberCard like %:name%")
	List<Card> findByName(String name);

	@Query("select c from Card c where c.numberCard like %:name%")
	List<Card> findByNameLikeIgnoreCase(String name);
}
