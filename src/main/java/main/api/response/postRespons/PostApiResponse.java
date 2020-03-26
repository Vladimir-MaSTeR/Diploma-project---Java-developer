package main.api.response.postRespons;


import com.fasterxml.jackson.annotation.JsonProperty;
import main.api.response.CommonResponse;

import java.util.List;

public class PostApiResponse implements CommonResponse {

    private int count;             //содержит общее количество постов, которое есть насайте
    @JsonProperty("posts")
    private List<PostApi> posts;


    public PostApiResponse() {
    }

    public PostApiResponse(int count, List<PostApi> posts) {
        this.count = count;
        this.posts = posts;
    }




    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public List<PostApi> getPosts() {
        return posts;
    }
    public void setPosts(List<PostApi> posts) {
        this.posts = posts;
    }
}
