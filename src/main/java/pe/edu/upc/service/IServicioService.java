package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Servicio;

public interface IServicioService {

	public void insert(Servicio s);

	List<Servicio> list();

	public void delete(int id);

	Optional<Servicio> listarId(int idServicio);

	List<Servicio> findByName(String name);

	List<Servicio> findByNameLikeIgnoreCase(String name);

}
