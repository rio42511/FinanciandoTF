package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;
import pe.edu.upc.entities.Advisory;

public interface IAdvisoryService {

	public void insert(Advisory ad);

	List<Advisory> list();

	public void delete(int id);

	Optional<Advisory> listarId(int idAdvisory);

	List<Advisory> findByCode(String code);

	List<Advisory> findByCodeLikeIgnoreCase(String code);
	
	public List<String[]> asesorTop();

	public List<String[]> servicioTop();

	public List<String[]> usuarioTop();

	public List<String[]> usuariosTopxServicio();
	
	public List<String[]> usuariosXservicio(String servicio);

}
