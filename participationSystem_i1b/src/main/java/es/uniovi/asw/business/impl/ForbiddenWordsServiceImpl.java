package es.uniovi.asw.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.business.ForbiddenWordsService;
import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.persistence.model.ForbiddenWords;

@Service
public class ForbiddenWordsServiceImpl implements ForbiddenWordsService{

	@Autowired
	private Factories factories;
	
	@Override
	public void save(ForbiddenWords word) {
		factories.getPersistenceFactory().getForbiddenWordsRepository().save(word);
	}

	@Override
	public void remove(ForbiddenWords word) {
		factories.getPersistenceFactory().getForbiddenWordsRepository().delete(word);
		
	}

	@Override
	public List<ForbiddenWords> findAll() {
		return factories.getPersistenceFactory().getForbiddenWordsRepository().findAll();


	}

}
