package main.repository;

        import main.model.Post;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Component;
        import org.springframework.stereotype.Repository;

        import java.time.LocalDateTime;
        import java.util.Date;
        import java.util.List;

@Repository
@Component
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT * FROM post p WHERE p.is_active = true " +
            "AND p.moderation_status = 'ACCEPTED' " +
            "AND time < CURDATE() " +
            "ORDER BY p.time DESC LIMIT ?2 OFFSET ?1", nativeQuery = true)
    public List<Post> allPostList(int offset, int limit);

    @Query(value = "SELECT * FROM post p WHERE p.is_active = true " +
            "AND p.moderation_status = 'ACCEPTED' " +
            "AND time < CURDATE() " +
            "AND (p.title LIKE '%' ?3 '%' OR p.text LIKE '%' ?3 '%') " +
            "ORDER BY p.time DESC LIMIT ?2 OFFSET ?1", nativeQuery = true)
    public List<Post> searchPost(int offset, int limit, String query);

    @Query(value = "SELECT * FROM post p WHERE p.is_active = true " +
            "AND p.moderation_status = 'ACCEPTED' " +
            "AND time < CURDATE() " +
            "AND p.time BETWEEN ?3 AND ?4 " +
            "ORDER BY p.time DESC LIMIT ?2 OFFSET ?1", nativeQuery = true)
    public List<Post> postsData(int offset, int limit, LocalDateTime date, LocalDateTime date2);

    @Query(value = "SELECT * FROM post p WHERE p.moderation_status = 'NEW' ", nativeQuery = true)
    public List<Post> postsNewStatus();

    @Query(value = "SELECT * FROM post p WHERE p.user_id_id = ?1", nativeQuery = true)
    public List<Post> postsUser(int id);

    @Query(value = "SELECT * FROM post p WHERE p.time BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Post> searchToDate(LocalDateTime date1, LocalDateTime date2);


}
