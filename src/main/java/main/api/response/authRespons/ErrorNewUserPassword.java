package main.api.response.authRespons;

import main.api.response.CommonResponse;

public class ErrorNewUserPassword implements CommonResponse {

    private String password;



    public ErrorNewUserPassword() {
    }

    public String getPassword() {
        return password;
    }




    public void setPassword(String password) {
        this.password = password;
    }
    public ErrorNewUserPassword(String password) {
        this.password = password;
    }
}
