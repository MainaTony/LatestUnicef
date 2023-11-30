package com.example.mediawatch.ApiResponse;

public class MediaWatch {
    String title;
    String date;
    String category;
    String storyurl;
    String summary;

    public MediaWatch() {
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public MediaWatch(String title, String date, String category, String storyurl, String summary) {
        this.title = title;
        this.date = date;
        this.category = category;
        this.storyurl = storyurl;
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStoryurl() {
        return storyurl;
    }

    public void setStoryurl(String storyurl) {
        this.storyurl = storyurl;
    }
}
