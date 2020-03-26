package main.api.response.authRespons;


import main.api.response.CommonResponse;

public class CaptchaResponse implements CommonResponse {

    private String  secret;
    private String image;




    public CaptchaResponse() {
    }

    public CaptchaResponse(String secret, String image) {
        this.secret = secret;
        this.image = image;
    }




    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
