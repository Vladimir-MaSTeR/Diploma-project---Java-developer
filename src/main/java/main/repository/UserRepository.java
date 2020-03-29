package main.repository;

import main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Component
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user u WHERE u.email = ?1 ", nativeQuery = true)
    List<User> searchEmail(String eMail);

    @Query(value = "SELECT * FROM user u WHERE u.code = ?1", nativeQuery = true)
    List<User> searchCode(String code);


}
