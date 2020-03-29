package main.api.response.authRespons;

import main.api.response.CommonResponse;

public class ErrorsEmail implements CommonResponse {

    private String email;



    public ErrorsEmail() {
    }

    public ErrorsEmail(String email) {
        this.email = email;
    }




    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
