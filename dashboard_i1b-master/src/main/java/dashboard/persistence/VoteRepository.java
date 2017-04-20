package dashboard.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dashboard.model.Vote;
import dashboard.model.VoteKey;

@Repository
public interface VoteRepository extends JpaRepository<Vote, VoteKey> {

}