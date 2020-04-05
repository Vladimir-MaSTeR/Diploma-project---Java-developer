package main.api.response.generalRespons;

import main.api.response.CommonResponse;

import java.util.List;

public class GeneralTagResponse implements CommonResponse {

    private List<GeneralTag> tags;



    public GeneralTagResponse() {
    }

    public GeneralTagResponse(List<GeneralTag> tags) {
        this.tags = tags;
    }



    public List<GeneralTag> getTags() {
        return tags;
    }
    public void setTags(List<GeneralTag> tags) {
        this.tags = tags;
    }
}
