package main.api.response.generalRespons;

import main.api.response.CommonResponse;

public class GeneralIdResponse implements CommonResponse {

    private int id;



    public GeneralIdResponse() {
    }

    public GeneralIdResponse(int id) {
        this.id = id;
    }




    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
