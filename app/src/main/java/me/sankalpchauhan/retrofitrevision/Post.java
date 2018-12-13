package me.sankalpchauhan.retrofitrevision;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int userID;

    private Integer ID;

    private String title;

    @SerializedName("body")
    private String text;

    public Post(int userID, String title, String text) {
        this.userID = userID;
        this.title = title;
        this.text = text;
    }

    public int getUserID() {
        return userID;
    }

    public Integer getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
