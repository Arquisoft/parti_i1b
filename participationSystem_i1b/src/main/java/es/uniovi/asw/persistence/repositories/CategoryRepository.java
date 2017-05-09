package es.uniovi.asw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uniovi.asw.persistence.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findByName(String name);

}
