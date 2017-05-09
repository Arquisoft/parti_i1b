package repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Citizen;

@Transactional
public interface CitizenDao extends JpaRepository<Citizen, Long> {

	Citizen findByEmailAndPassword(String email, String password);
}
