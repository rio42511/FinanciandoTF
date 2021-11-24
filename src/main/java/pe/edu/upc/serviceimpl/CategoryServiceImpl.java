package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Category;
import pe.edu.upc.repository.CategoryRepository;
import pe.edu.upc.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository cR;

	@Override
	@Transactional
	public void insert(Category c) {
		cR.save(c);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

	@Override
	@Transactional
	public void delete(int id) {
		cR.deleteById(id);
	}

	@Override
	public Optional<Category> listarId(int idCategory) {
		// TODO Auto-generated method stub
		return cR.findById(idCategory);
	}

	@Override
	public List<Category> findByName(String name) {
		// TODO Auto-generated method stub
		return cR.findByName(name);
	}

	@Override
	public List<Category> findByNameLikeIgnoreCase(String name) {
		// TODO Auto-generated method stub
		return cR.findByNameLikeIgnoreCase(name);
	}

}
