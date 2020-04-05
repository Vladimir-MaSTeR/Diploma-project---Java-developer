package main.api.response.errorRespons;

import main.api.response.CommonResponse;

public class ErrorName implements CommonResponse {

    private String name;


    public ErrorName() {
    }

    public ErrorName(String name) {
        this.name = name;
    }



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
