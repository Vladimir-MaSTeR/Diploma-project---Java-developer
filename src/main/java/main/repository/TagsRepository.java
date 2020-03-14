package main.repository;

import main.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface TagsRepository extends JpaRepository<Tags, Integer> {
}
