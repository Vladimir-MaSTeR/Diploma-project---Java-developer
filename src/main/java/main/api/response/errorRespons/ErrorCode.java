package main.api.response.errorRespons;

import main.api.response.CommonResponse;

public class ErrorCode implements CommonResponse {

    private String code;



    public ErrorCode() {
    }

    public ErrorCode(String code) {
        this.code = code;
    }




    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
