package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Card;
import pe.edu.upc.repository.CardRepository;
import pe.edu.upc.service.ICardService;

@Service
public class CardServiceImpl implements ICardService {

	@Autowired
	private CardRepository cR;

	@Override
	@Transactional
	public void insert(Card c) {
		cR.save(c);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Card> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

	@Override
	@Transactional
	public void delete(int id) {
		cR.deleteById(id);
	}

	@Override
	public Optional<Card> listarId(int idCard) {
		// TODO Auto-generated method stub
		return cR.findById(idCard);
	}

	@Override
	public List<Card> findByName(String name) {
		// TODO Auto-generated method stub
		return cR.findByName(name);
	}

	@Override
	public List<Card> findByNameLikeIgnoreCase(String name) {
		// TODO Auto-generated method stub
		return cR.findByNameLikeIgnoreCase(name);
	}

}
