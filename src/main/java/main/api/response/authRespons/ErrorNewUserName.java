package main.api.response.authRespons;

import main.api.response.CommonResponse;

public class ErrorNewUserName implements CommonResponse {

    private String name;


    public ErrorNewUserName() {
    }

    public ErrorNewUserName(String name) {
        this.name = name;
    }



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
