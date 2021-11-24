package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Membership;
import pe.edu.upc.repository.MembershipRepository;
import pe.edu.upc.service.IMembershipService;

@Service
public class MembershipServiceImpl implements IMembershipService {

	@Autowired
	private MembershipRepository mR;

	@Override
	@Transactional
	public void insert(Membership m) {
		mR.save(m);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Membership> list() {
		return mR.findAll();
	}

	@Override
	@Transactional
	public void delete(int id) {
		mR.deleteById(id);

	}

	@Override
	public Optional<Membership> listarId(int idMembership) {
		// TODO Auto-generated method stub
		return mR.findById(idMembership);
	}

	@Override
	public List<Membership> findByName(String name) {
		// TODO Auto-generated method stub
		return mR.findByName(name);
	}

	@Override
	public List<Membership> findByNameLikeIgnoreCase(String name) {
		// TODO Auto-generated method stub
		return mR.findByNameLikeIgnoreCase(name);
	}
}
