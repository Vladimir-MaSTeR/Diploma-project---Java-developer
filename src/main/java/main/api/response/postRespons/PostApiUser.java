package main.api.response.postRespons;

import main.api.response.CommonResponse;

public class PostApiUser implements CommonResponse {

    private int id;
    private String name;


    public PostApiUser() {
    }

    public PostApiUser(int id, String name) {
        this.id = id;
        this.name = name;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
