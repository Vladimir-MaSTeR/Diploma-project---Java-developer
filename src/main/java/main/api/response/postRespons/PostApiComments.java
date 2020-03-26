package main.api.response.postRespons;

import main.api.response.CommonResponse;

import java.time.LocalDateTime;
import java.util.Date;

public class PostApiComments implements CommonResponse {

    private int id;
    private LocalDateTime time;
    private String text;
    private PostApiUserComments user;


    public PostApiComments() {
    }

    public PostApiComments(int id, LocalDateTime time, String text, PostApiUserComments user) {
        this.id = id;
        this.time = time;
        this.text = text;
        this.user = user;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
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
