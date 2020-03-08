package main.api.response.postRespons;

import java.util.Date;
import java.util.List;

public class PostApi implements CommonResponse {

    private int postId;
    private Date time;
    private PostApiUser IdAndNameUser;
    private String title;
    private String announce;
    private int likeCount;
    private int dislikeCount;
    private int commentCount;
    private int viewCount;



    public PostApi() {
    }

    public PostApi(int postId, Date time, PostApiUser IdAndNameUser, String title, String announce, int likeCount, int dislikeCount, int commentCount, int viewCount) {
        this.postId = postId;
        this.time = time;
        this.IdAndNameUser = IdAndNameUser;
        this.title = title;
        this.announce = announce;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
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

    public PostApiUser getIdAndNameUser() {
        return IdAndNameUser;
    }
    public void setIdAndNameUser(PostApiUser IdAndNameUser) {
        this.IdAndNameUser = IdAndNameUser;
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
