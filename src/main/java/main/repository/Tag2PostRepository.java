package main.repository;

import main.model.Tag2Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface Tag2PostRepository extends JpaRepository<Tag2Post, Integer> {

    @Query(value = "SELECT * FROM tag2post tp WHERE tp.tag_id_id = ?1", nativeQuery = true)
    List<Tag2Post> searchIdTag(int idTag);
}
