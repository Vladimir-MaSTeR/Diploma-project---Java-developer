package main.api.response.generalRespons;

import main.api.response.CommonResponse;

public class GeneralSettingsResponse implements CommonResponse {

    private boolean multiuser_MODE;
    private boolean post_PREMODERATION;
    private boolean statistics_IS_PUBLIC;



    public GeneralSettingsResponse() {
    }

    public GeneralSettingsResponse(boolean multiuser_MODE, boolean post_PREMODERATION, boolean statistics_IS_PUBLIC) {
        this.multiuser_MODE = multiuser_MODE;
        this.post_PREMODERATION = post_PREMODERATION;
        this.statistics_IS_PUBLIC = statistics_IS_PUBLIC;
    }




    public boolean isMultiuser_MODE() {
        return multiuser_MODE;
    }
    public void setMultiuser_MODE(boolean multiuser_MODE) {
        this.multiuser_MODE = multiuser_MODE;
    }

    public boolean isPost_PREMODERATION() {
        return post_PREMODERATION;
    }
    public void setPost_PREMODERATION(boolean post_PREMODERATION) {
        this.post_PREMODERATION = post_PREMODERATION;
    }

    public boolean isStatistics_IS_PUBLIC() {
        return statistics_IS_PUBLIC;
    }
    public void setStatistics_IS_PUBLIC(boolean statistics_IS_PUBLIC) {
        this.statistics_IS_PUBLIC = statistics_IS_PUBLIC;
    }
}
