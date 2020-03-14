package main.repository;

import main.model.PostComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface PostCommentsRepository extends JpaRepository<PostComments, Integer> {
}
