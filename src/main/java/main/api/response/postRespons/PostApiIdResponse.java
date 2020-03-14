package main.api.response.postRespons;

import java.util.Date;
import java.util.List;

public class PostApiIdResponse implements CommonResponse {

    private int id;
    private Date time;
    private PostApiUser user;
    private String title;
    private String text;
    private int likeCount;
    private int dislikeCount;
    private int viewCount;
    private PostApiComments comments;
    private PostApiTagName tags;




    public PostApiIdResponse() {
    }

    public PostApiIdResponse(int id, Date time, PostApiUser user, String title, String text, int likeCount, int dislikeCount, int viewCount, PostApiComments comments, PostApiTagName tags) {
        this.id = id;
        this.time = time;
        this.user = user;
        this.title = title;
        this.text = text;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.viewCount = viewCount;
        this.comments = comments;
        this.tags = tags;
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

    public PostApiUser getUser() {
        return user;
    }
    public void setUser(PostApiUser user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
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

    public int getViewCount() {
        return viewCount;
    }
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public PostApiComments getComments() {
        return comments;
    }
    public void setComments(PostApiComments comments) {
        this.comments = comments;
    }

    public PostApiTagName getTags() {
        return tags;
    }
    public void setTags(PostApiTagName tags) {
        this.tags = tags;
    }



}
