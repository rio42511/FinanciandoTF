package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Membership;

public interface IMembershipService {

	public void insert(Membership mb);

	List<Membership> list();

	public void delete(int id);

	Optional<Membership> listarId(int idMembership);

	List<Membership> findByName(String name);

	List<Membership> findByNameLikeIgnoreCase(String name);
}
