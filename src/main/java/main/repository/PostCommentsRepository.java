package main.repository;

import main.model.PostComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface PostCommentsRepository extends JpaRepository<PostComments, Integer> {

    @Query(value = "SELECT * FROM post_comments pc WHERE pc.parent_id = ?1", nativeQuery = true)
    List<PostComments> searchParentId(int parentId);

}
