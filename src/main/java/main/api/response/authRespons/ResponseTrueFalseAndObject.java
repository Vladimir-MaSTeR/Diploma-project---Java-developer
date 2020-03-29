package main.api.response.authRespons;

import main.api.response.CommonResponse;

import java.util.List;

public class ResponseTrueFalseAndObject implements CommonResponse {

    private boolean result;
    private Object errors;



    public ResponseTrueFalseAndObject() {
    }

    public ResponseTrueFalseAndObject(boolean result) {
        this.result = result;
    }

    public ResponseTrueFalseAndObject(boolean result, Object errors) {
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
