package main.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PostVotes { // Лайки и дизлайки постов

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

  //  @Column(nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private User userId;  // тот, кто поставил лайк / дизлайк

    //@Column(nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", nullable = false)
    private Post postId;  // пост, которому поставлен лайк / дизлайк

    @Column(nullable = false)
   // @JsonFormat(pattern="yyyy-MM-dd")
    private Date time;   // дата и время лайка / дизлайка

    @Column(nullable = false)
    private byte value;  //  лайк или дизлайк: 1 или -1


    public PostVotes() {
    }

    public PostVotes(int id, User userId, Post postId, Date time, byte value) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.time = time;
        this.value = value;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }
    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Post getPostId() {
        return postId;
    }
    public void setPostId(Post postId) {
        this.postId = postId;
    }

    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }

    public byte getValue() {
        return value;
    }
    public void setValue(byte value) {
        this.value = value;
    }
}
