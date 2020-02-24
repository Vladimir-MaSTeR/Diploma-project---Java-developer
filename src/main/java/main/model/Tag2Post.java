package main.model;


import javax.persistence.*;

@Entity
public class Tag2Post { // связи тэгов с постами

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private int postId;

    @Column(nullable = false)
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
