package com.myapplicationdev.android.ndpsongs;

import java.io.Serializable;

public class Songs implements Serializable {

    private int id;
    private String title;
    private String singers;
    private String year;
    private int stars;



    public Songs(int id, String title, String singers,String year,int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getSingers() { return singers; }
    public String getYear() { return year; }
    public int getStars() { return stars; }
    public void setTitle(String title) { this.title = title; }
    public void setSingers(String singers) { this.singers = singers; }

    @Override
    public String toString() {
        return "Song name " + title + "\n " + "Song artist " + singers + "\n" + "Song Year " + year + "\n" + "Song Stars " + stars;
    }
}
