package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Card;

public interface ICardService {
	public void insert(Card c);

	List<Card> list();

	public void delete(int id);

	Optional<Card> listarId(int idCard);

	List<Card> findByName(String name);

	List<Card> findByNameLikeIgnoreCase(String name);
}
