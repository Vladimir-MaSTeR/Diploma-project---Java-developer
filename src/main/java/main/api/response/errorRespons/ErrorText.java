package main.api.response.errorRespons;

import main.api.response.CommonResponse;

public class ErrorText implements CommonResponse {

    private String text;




    public ErrorText() {
    }

    public ErrorText(String text) {
        this.text = text;
    }




    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
