package es.uniovi.asw.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.business.VoteService;
import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.persistence.model.Citizen;
import es.uniovi.asw.persistence.model.Vote;

@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private Factories factories;

	@Override
	public void save(Vote vote) {
		factories.getPersistenceFactory().getVoteRepository().save(vote);
	}

	// cast it to voteComment when u iterate over it
	@Override
	public List<Vote> findCommentVotesByCitizen(Citizen citizen) {
		return factories.getPersistenceFactory().getVoteRepository().findCommentVotesByCitizen(citizen);
	}

	// cast it to ProposalComment when u iterate over it
	@Override
	public List<Vote> findProposalVotesByCitizen(Citizen citizen) {
		return factories.getPersistenceFactory().getVoteRepository().findProposalVotesByCitizen(citizen);
	}

	@Override
	public List<Vote> findAllProposalVotes() {
		return factories.getPersistenceFactory().getVoteRepository().findAllProposalVotes();
	}
	
	

}
