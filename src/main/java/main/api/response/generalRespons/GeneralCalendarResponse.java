package main.api.response.generalRespons;

import main.api.response.CommonResponse;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class GeneralCalendarResponse implements CommonResponse {

    private List<String> years;
    private HashMap<LocalDate, Integer> posts;




    public GeneralCalendarResponse() {
    }

    public GeneralCalendarResponse(List<String> years, HashMap<LocalDate, Integer> posts) {
        this.years = years;
        this.posts = posts;
    }




    public List<String> getYears() {
        return years;
    }
    public void setYears(List<String> years) {
        this.years = years;
    }

    public HashMap<LocalDate, Integer> getPosts() {
        return posts;
    }
    public void setPosts(HashMap<LocalDate, Integer> posts) {
        this.posts = posts;
    }
}
