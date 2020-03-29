package main.api.request;

import main.api.response.CommonResponse;

public class EmailRecoveryRequestApi implements CommonResponse {

    private String email;



    public EmailRecoveryRequestApi() {
    }

    public EmailRecoveryRequestApi(String email) {
        this.email = email;
    }



    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
