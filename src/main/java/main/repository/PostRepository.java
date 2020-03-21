package main.repository;

        import main.model.Post;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Component;
        import org.springframework.stereotype.Repository;

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
            "AND p.moderation_status = 'ACCEPTED'" +
            "AND time < CURDATE()" +
            "AND p.time = ?3 " +
            "ORDER BY p.time DESC LIMIT ?2 OFFSET ?1", nativeQuery = true)
    public List<Post> postsData(int offset, int limit, Date date);

}
