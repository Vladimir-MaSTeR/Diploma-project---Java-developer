package main.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PostComments { // комментарии к постам

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(columnDefinition = "INTEGER")
    private Integer parentId; // комментарий, на который оставлен этот комментарий (может быть NULL, если комментарий оставлен просто к посту)

   // @Column(nullable = false)
    @ManyToOne()
    @JoinColumn(nullable = false)
    private Post postId;   // пост, к которому написан комментарий

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private User userId;   // автор комментария

    @Column(nullable = false)
    private Date time;    // дата и время комментария


    public PostComments() {
    }

    public PostComments(int id, int parentId, Post postId, User userId, Date time) {
        this.id = id;
        this.parentId = parentId;
        this.postId = postId;
        this.userId = userId;
        this.time = time;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Post getPostId() {
        return postId;
    }
    public void setPostId(Post postId) {
        this.postId = postId;
    }

    public User getUserId() {
        return userId;
    }
    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
}
