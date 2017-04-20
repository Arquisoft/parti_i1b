package dashboard.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dashboard.model.Proposal;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
	
}