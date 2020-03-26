package main.api.response.authRespons;

import main.api.response.CommonResponse;

public class ErrorsNewUserEmail implements CommonResponse {

    private String email;



    public ErrorsNewUserEmail() {
    }

    public ErrorsNewUserEmail(String email) {
        this.email = email;
    }




    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
