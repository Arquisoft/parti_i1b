package es.uniovi.asw.business;

import java.util.List;

import es.uniovi.asw.persistence.model.Category;

public interface CategoryService {

	void save(Category category);

	void delete(Category category);

	List<Category> findAll();
	// Category findCategory(String Category);

}
