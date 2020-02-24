package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class PostVotes { // Лайки и дизлайки постов

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;  // тот, кто поставил лайк / дизлайк\
    private int postId;  // пост, которому поставлен лайк / дизлайк
    private Date time;   // дата и время лайка / дизлайка
    private byte value;  //  лайк или дизлайк: 1 или -1


    public PostVotes(int id, int userId, int postId, Date time, byte value) {
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

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
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
