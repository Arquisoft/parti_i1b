package es.uniovi.asw.persistence.repositories;

public interface PersistenceFactory {

	CitizenRepository getCitizenRepository();

	VoteRepository getVoteRepository();

	CommentRepository getCommentRepository();

	ProposalRepository getProposalRepository();

	CategoryRepository getCategoryRepository();

	ConfigurationRepository getConfigurationRepository();

	ForbiddenWordsRepository getForbiddenWordsRepository();

	AdministratorRepository getAdministratorRepository();
}
