package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Bank;

public interface IBankService {

	public void insert(Bank mc);

	List<Bank> list();

	public void delete(int id);

	Optional<Bank> listarId(int idBank);

	List<Bank> findByName(String name);

	List<Bank> findByNameLikeIgnoreCase(String name);

}
