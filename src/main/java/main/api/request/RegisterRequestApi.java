package main.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import main.api.response.CommonResponse;

public class RegisterRequestApi implements CommonResponse {

    @JsonProperty("e_mail")
    private String eMail;
    private String name;
    private String password;
    private String captcha;
    @JsonProperty("captcha_secret")
    private String captchaSecret;



    public RegisterRequestApi() {
    }

    public RegisterRequestApi(String eMail, String name, String password, String captcha, String captchaSecret) {
        this.eMail = eMail;
        this.name = name;
        this.password = password;
        this.captcha = captcha;
        this.captchaSecret = captchaSecret;
    }



    public String geteMail() {
        return eMail;
    }
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCaptchaSecret() {
        return captchaSecret;
    }
    public void setCaptchaSecret(String captchaSecret) {
        this.captchaSecret = captchaSecret;
    }
}
