package main.api.response.errorRespons;

import main.api.response.CommonResponse;

public class ErrorPassword implements CommonResponse {

    private String password;



    public ErrorPassword() {
    }

    public String getPassword() {
        return password;
    }




    public void setPassword(String password) {
        this.password = password;
    }
    public ErrorPassword(String password) {
        this.password = password;
    }
}
