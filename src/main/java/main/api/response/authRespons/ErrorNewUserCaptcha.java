package main.api.response.authRespons;

import main.api.response.CommonResponse;

public class ErrorNewUserCaptcha implements CommonResponse {

    private String captcha;


    public ErrorNewUserCaptcha() {
    }

    public ErrorNewUserCaptcha(String captcha) {
        this.captcha = captcha;
    }


    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
