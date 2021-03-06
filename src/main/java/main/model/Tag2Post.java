package main.model;


import javax.persistence.*;

@Entity
public class Tag2Post { // связи тэгов с постами

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

   // @Column(nullable = false)
    @ManyToOne
    @JoinColumn(nullable = false)
    private Post postId;

    //@Column(nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Tags tagId;



    public Tag2Post(int id, Post postId, Tags tagId) {
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

    public Post getPostId() {
        return postId;
    }
    public void setPostId(Post postId) {
        this.postId = postId;
    }

    public Tags getTagId() {
        return tagId;
    }
    public void setTagId(Tags tagId) {
        this.tagId = tagId;
    }
}
