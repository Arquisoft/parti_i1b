package es.uniovi.asw.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.asw.business.AdministratorService;
import es.uniovi.asw.business.CategoryService;
import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.business.CommentService;
import es.uniovi.asw.business.ConfigurationService;
import es.uniovi.asw.business.ForbiddenWordsService;
import es.uniovi.asw.business.ProposalService;
import es.uniovi.asw.business.ServicesFactory;
import es.uniovi.asw.business.VoteService;

@Component
public class ServicesFactoryImpl implements ServicesFactory {

	@Autowired
	private VoteService voteService;
	@Autowired
	private CitizenService citizenService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ProposalService proposalService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ConfigurationService configurationService;
	@Autowired
	private ForbiddenWordsService forbiddenWordsService;
	@Autowired
	private AdministratorService administratorService;

	@Override
	public CitizenService getCitizenService() {
		return citizenService;
	}

	@Override
	public VoteService getVoteService() {
		return voteService;
	}

	@Override
	public CommentService getCommentService() {
		return commentService;
	}

	@Override
	public ProposalService getProposalService() {
		return proposalService;
	}

	@Override
	public CategoryService getCategoryService() {
		return categoryService;
	}

	@Override
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	@Override
	public ForbiddenWordsService getForbiddenWordsService() {
		return forbiddenWordsService;
	}

	@Override
	public AdministratorService getAdministratorService() {
		return administratorService;
	}

}
