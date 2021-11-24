package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Servicio;
import pe.edu.upc.repository.ServicioRepository;
import pe.edu.upc.service.IServicioService;

@Service
public class ServicioServiceImpl implements IServicioService {

	@Autowired
	private ServicioRepository sR;

	@Override
	@Transactional
	public void insert(Servicio s) {
		sR.save(s);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servicio> list() {
		return sR.findAll();

	}

	@Override
	@Transactional
	public void delete(int id) {
		sR.deleteById(id);
	}

	@Override
	public Optional<Servicio> listarId(int idServicio) {
		// TODO Auto-generated method stub
		return sR.findById(idServicio);
	}

	@Override
	public List<Servicio> findByName(String name) {
		// TODO Auto-generated method stub
		return sR.findByName(name);
	}

	@Override
	public List<Servicio> findByNameLikeIgnoreCase(String name) {
		// TODO Auto-generated method stub
		return sR.findByNameLikeIgnoreCase(name);
	}

}
