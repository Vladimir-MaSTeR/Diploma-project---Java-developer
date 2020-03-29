package main.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import main.api.response.CommonResponse;

public class LoginRequestApi implements CommonResponse {

    @JsonProperty("e_mail")
    private String email;
    private String password;



    public LoginRequestApi() {
    }

    public LoginRequestApi(String email, String password) {
        this.email = email;
        this.password = password;
    }




    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
