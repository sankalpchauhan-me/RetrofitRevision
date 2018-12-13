package me.sankalpchauhan.retrofitrevision;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int userID;

    private int ID;

    private String title;

    @SerializedName("body")
    private String text;

    public int getUserID() {
        return userID;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
