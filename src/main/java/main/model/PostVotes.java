package main.model;

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
    private Users userId;  // тот, кто поставил лайк / дизлайк

    //@Column(nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", nullable = false)
    private Posts postId;  // пост, которому поставлен лайк / дизлайк

    @Column(nullable = false)
    private Date time;   // дата и время лайка / дизлайка

    @Column(nullable = false)
    private byte value;  //  лайк или дизлайк: 1 или -1




    public PostVotes(int id, Users userId, Posts postId, Date time, byte value) {
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

    public Users getUserId() {
        return userId;
    }
    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Posts getPostId() {
        return postId;
    }
    public void setPostId(Posts postId) {
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
