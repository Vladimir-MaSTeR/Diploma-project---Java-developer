package main.repository;

import main.model.PostVotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


@Repository
@Component
public interface PostVotesRepository extends JpaRepository<PostVotes, Integer> {

    @Query(value = "SELECT * FROM post_votes pv WHERE pv.post_id = ?1", nativeQuery = true)
    public List<PostVotes> postVotesIdPost(int idPost);




}
