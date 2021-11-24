package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Advisory;
import pe.edu.upc.repository.AdvisoryRepository;
import pe.edu.upc.service.IAdvisoryService;

@Service
public class AdvisoryServiceImpl implements IAdvisoryService{
	
	@Autowired
	private AdvisoryRepository aR;
	
	@Override
	@Transactional
	public void insert(Advisory a) {
		aR.save(a);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Advisory> list() {
		return aR.findAll();
	}
	
	@Override
	@Transactional
	public void delete(int id) {
		aR.deleteById(id);

	}

	@Override
	public Optional<Advisory> listarId(int idAdviser) {
		// TODO Auto-generated method stub
		return aR.findById(idAdviser);
	}

	@Override
	public List<Advisory> findByCode(String code) {
		// TODO Auto-generated method stub
		return aR.findByCode(code);
	}

	@Override
	public List<Advisory> findByCodeLikeIgnoreCase(String code) {
		// TODO Auto-generated method stub
		return aR.findByCodeLikeIgnoreCase(code);
	}
/*
	@Override
	public List<String[]> asesorTop()
	{
		
		return aR.asesorTop();
	}
	
	@Override
	public List<String[]> servicioTop()
	{
		
		return aR.servicioTop();
	}
	
	@Override
	public List<String[]> usuarioTop()
	{
		
		return aR.usuarioTop();
		
	}
	@Override
	public List<String[]> usuariosTopxServicio()
	{
		return aR.usuariosTopxServicio();
		
	}
	
	@Override
	public List<String[]> usuariosXservicio(String servicio)
	{
		return aR.usuariosXservicio(servicio);
		
	}
*/
}
