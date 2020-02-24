package main.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tag2Post { // связи тэгов с постами

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int postId;
    private int tagId;



    public Tag2Post(int id, int postId, int tagId) {
        this.id = id;
        this.postId = postId;
        this.tagId = tagId;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getTagId() {
        return tagId;
    }
    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
