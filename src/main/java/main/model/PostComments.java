package main.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PostComments { // комментарии к постам

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    private int parentId; // комментарий, на который оставлен этот комментарий (может быть NULL, если комментарий оставлен просто к посту)

   // @Column(nullable = false)
    @ManyToOne()
    @JoinColumn(nullable = false)
    private Posts postId;   // пост, к которому написан комментарий

    @Column(nullable = false)
    private int userId;   // автор комментария

    @Column(nullable = false)
    private Date time;    // дата и время комментария


    public PostComments() {
    }

    public PostComments(int id, int parentId, Posts postId, int userId, Date time) {
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

    public Posts getPostId() {
        return postId;
    }
    public void setPostId(Posts postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
}
