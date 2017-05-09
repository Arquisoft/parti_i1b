package es.uniovi.asw.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.business.CommentService;
import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.persistence.model.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private Factories factories;

	@Override
	public void save(Comment comment) {
		factories.getPersistenceFactory().getCommentRepository().save(comment);

	}

	@Override
	public void delete(Comment comment) {
		factories.getPersistenceFactory().getCommentRepository().delete(comment);

	}

}
