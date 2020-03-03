package main.api.response.postRespons;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostApiResponse {

    private int count;             //содержит общее количество постов, которое есть насайте
    @JsonProperty("data")
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
