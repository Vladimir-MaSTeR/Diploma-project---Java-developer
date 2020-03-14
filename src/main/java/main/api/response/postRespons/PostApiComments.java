package main.api.response.postRespons;

import java.util.Date;

public class PostApiComments implements CommonResponse {

    private int id;
    private Date time;
    private PostApiUserComments user;
    private String text;

    public PostApiComments() {
    }

    public PostApiComments(int id, Date time, PostApiUserComments user, String text) {
        this.id = id;
        this.time = time;
        this.user = user;
        this.text = text;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }

    public PostApiUserComments getUser() {
        return user;
    }
    public void setUser(PostApiUserComments user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
