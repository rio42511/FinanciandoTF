package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Bank;
import pe.edu.upc.repository.BankRepository;
import pe.edu.upc.service.IBankService;

@Service
public class BankServiceImpl implements IBankService {

	@Autowired
	private BankRepository mR;

	@Override
	@Transactional
	public void insert(Bank bn) {
		mR.save(bn);

	}

	@Override
	@Transactional
	public List<Bank> list() {
		return mR.findAll();

	}

	@Override
	@Transactional
	public void delete(int id) {
		mR.deleteById(id);
	}

	@Override
	public Optional<Bank> listarId(int idBank) {
		// TODO Auto-generated method stub
		return mR.findById(idBank);
	}

	@Override
	public List<Bank> findByName(String name) {
		// TODO Auto-generated method stub
		return mR.findByphone(name);
	}

	@Override
	public List<Bank> findByNameLikeIgnoreCase(String name) {
		// TODO Auto-generated method stub
		return mR.findByphoneLikeIgnoreCase(name);
	}

}
