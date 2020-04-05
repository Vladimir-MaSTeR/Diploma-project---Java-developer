package main.repository;

import main.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface TagsRepository extends JpaRepository<Tags, Integer> {

    @Query(value = "SELECT * FROM tags t WHERE t.name = ?1", nativeQuery = true)
    List<Tags> searchTag(String query);

}
