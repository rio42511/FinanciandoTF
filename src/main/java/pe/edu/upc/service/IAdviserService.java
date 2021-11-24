package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Adviser;

public interface IAdviserService {

	public void insert(Adviser ad);

	List<Adviser> list();

	public void delete(int id);

	Optional<Adviser> listarId(int idAdviser);

	List<Adviser> findByName(String name);

	List<Adviser> findByNameLikeIgnoreCase(String name);

}
