package main.api.response.authRespons;

import main.api.response.CommonResponse;

public class ErrorCaptcha implements CommonResponse {

    private String captcha;


    public ErrorCaptcha() {
    }

    public ErrorCaptcha(String captcha) {
        this.captcha = captcha;
    }


    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
