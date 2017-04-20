package dashboard.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dashboard.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}