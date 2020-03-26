package main.api.response.postRespons;

import main.api.response.CommonResponse;

import java.time.LocalDateTime;
import java.util.Date;

public class PostApi implements CommonResponse {

    private int id;
    private LocalDateTime time;
    private PostApiUser user;
    private String title;
    private String announce;
    private int likeCount;
    private int dislikeCount;
    private int commentCount;
    private int viewCount;



    public PostApi() {
    }

    public PostApi(int id, LocalDateTime time, PostApiUser user, String title, String announce, int likeCount, int dislikeCount, int commentCount, int viewCount) {
        this.id = id;
        this.time = time;
        this.user = user;
        this.title = title;
        this.announce = announce;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
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

    public PostApiUser getUser() {
        return user;
    }
    public void setUser(PostApiUser IdAndNameUser) {
        this.user = IdAndNameUser;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnounce() {
        return announce;
    }
    public void setAnnounce(String announce) {
        this.announce = announce;
    }

    public int getLikeCount() {
        return likeCount;
    }
    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }
    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getViewCount() {
        return viewCount;
    }
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
