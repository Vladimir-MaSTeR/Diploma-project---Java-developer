package main.api.response.authRespons;

import main.api.response.CommonResponse;

import java.util.List;

public class NewUserResponse implements CommonResponse {

    private boolean result;
    private Object errors;



    public NewUserResponse() {
    }

    public NewUserResponse(boolean result) {
        this.result = result;
    }

    public NewUserResponse(boolean result, Object errors) {
        this.result = result;
        this.errors = errors;
    }

    public boolean isResult() {
        return result;
    }
    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getErrors() {
        return errors;
    }
    public void setErrors(Object errors) {
        this.errors = errors;
    }
}
