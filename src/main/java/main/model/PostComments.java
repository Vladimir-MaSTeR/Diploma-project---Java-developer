package main.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class PostComments { // комментарии к постам

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(columnDefinition = "INTEGER")
    private Integer parentId; // комментарий, на который оставлен этот комментарий (может быть NULL, если комментарий оставлен просто к посту)

    @ManyToOne()
    @JoinColumn(nullable = false)
    private Post postId;   // пост, к которому написан комментарий

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private User userId;   // автор комментария

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime time;    // дата и время комментария

    @Column(nullable = false)
    private String text; // текст комментария


    public PostComments() {
    }

    public PostComments(int parentId, Post postId, User userId, LocalDateTime time, String text) {
        this.parentId = parentId;
        this.postId = postId;
        this.userId = userId;
        this.time = time;
        this.text = text;
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

    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }
    public void setText (String text) {
        this.text = text;
    }
}
