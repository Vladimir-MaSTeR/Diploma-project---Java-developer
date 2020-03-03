package main.repository;

import main.model.PostVotes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostVotesRepository extends JpaRepository<PostVotes, Integer> {



}
