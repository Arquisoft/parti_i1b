package es.uniovi.asw.business;

public interface ServicesFactory {
	
	CitizenService getCitizenService();
	VoteService getVoteService();
	CommentService getCommentService();
	ProposalService getProposalService();
	CategoryService getCategoryService();
	ConfigurationService getConfigurationService();
	ForbiddenWordsService getForbiddenWordsService();
	AdministratorService getAdministratorService();
}
