package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class PostComments { // комментарии к постам

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int parentId; // комментарий, на который оставлен этот комментарий (может быть NULL, если комментарий оставлен просто к посту)
    private int postId;   // пост, к которому написан комментарий
    private int userId;   // автор комментария
    private Date time;    // дата и время комментария


    public PostComments(int id, int parentId, int postId, int userId, Date time) {
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

    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
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
