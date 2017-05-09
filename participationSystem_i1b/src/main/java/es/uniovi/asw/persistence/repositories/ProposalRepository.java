package es.uniovi.asw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uniovi.asw.persistence.model.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {

	Proposal findByTitle(String title);

}
