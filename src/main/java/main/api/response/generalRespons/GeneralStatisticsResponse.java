package main.api.response.generalRespons;

import main.api.response.CommonResponse;

public class GeneralStatisticsResponse implements CommonResponse {

    private int postsCount;
    private int likesCount;
    private int dislikesCount;
    private int viewsCount;
    private String firstPublication;




    public GeneralStatisticsResponse() {
    }

    public GeneralStatisticsResponse(int postsCount, int likesCount, int dislikesCount, int viewsCount, String firstPublication) {
        this.postsCount = postsCount;
        this.likesCount = likesCount;
        this.dislikesCount = dislikesCount;
        this.viewsCount = viewsCount;
        this.firstPublication = firstPublication;
    }





    public int getPostsCount() {
        return postsCount;
    }
    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    public int getLikesCount() {
        return likesCount;
    }
    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getDislikesCount() {
        return dislikesCount;
    }
    public void setDislikesCount(int dislikesCount) {
        this.dislikesCount = dislikesCount;
    }

    public int getViewsCount() {
        return viewsCount;
    }
    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public String getFirstPublication() {
        return firstPublication;
    }
    public void setFirstPublication(String firstPublication) {
        this.firstPublication = firstPublication;
    }
}
