package es.uniovi.asw.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.uniovi.asw.persistence.model.Citizen;
import es.uniovi.asw.persistence.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

	@Query("select u from Vote u where u.citizen = :citizen and Type(u) = 'C'")
	List<Vote> findCommentVotesByCitizen(@Param("citizen") Citizen citizen);

	@Query("select u from Vote u where u.citizen = :citizen and Type(u) = 'P'")
	List<Vote> findProposalVotesByCitizen(@Param("citizen") Citizen citizen);
	
	@Query("select u from Vote u where Type(u) = 'P'")
	List<Vote> findAllProposalVotes();
}
