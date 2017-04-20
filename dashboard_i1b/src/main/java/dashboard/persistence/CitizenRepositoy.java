package dashboard.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dashboard.model.Citizen;

@Repository
public interface CitizenRepositoy extends JpaRepository<Citizen, Long> {
	
	Citizen findByEmailAndPassword(String email, String password);
	
} 