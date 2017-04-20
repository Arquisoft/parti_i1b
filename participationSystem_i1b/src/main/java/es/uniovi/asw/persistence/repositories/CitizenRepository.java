package es.uniovi.asw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uniovi.asw.persistence.model.Citizen;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {

	Citizen findByEmail(String email);
	
}
