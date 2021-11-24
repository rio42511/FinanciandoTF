package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.upc.entities.Adviser;
import pe.edu.upc.repository.AdviserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.service.IAdviserService;

@Service
public class AdviserServiceImpl implements IAdviserService {

	@Autowired
	private AdviserRepository aR;

	@Override
	@Transactional
	public void insert(Adviser a) {
		aR.save(a);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Adviser> list() {
		return aR.findAll();
	}

	@Override
	@Transactional
	public void delete(int id) {
		aR.deleteById(id);

	}

	@Override
	public Optional<Adviser> listarId(int idAdviser) {
		// TODO Auto-generated method stub
		return aR.findById(idAdviser);
	}

	@Override
	public List<Adviser> findByName(String name) {
		// TODO Auto-generated method stub
		return aR.findByName(name);
	}

	@Override
	public List<Adviser> findByNameLikeIgnoreCase(String name) {
		// TODO Auto-generated method stub
		return aR.findByNameLikeIgnoreCase(name);
	}

}
