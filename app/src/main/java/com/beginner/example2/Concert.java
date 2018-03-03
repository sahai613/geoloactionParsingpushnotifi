package com.beginner.example2;

/**
 * Created by sahai613 on 20-10-2017.
 */

public class Concert {
    private String title,link,imageLink;

    public Concert(String title, String link, String imageLink) {
        this.title = title;
        this.link = link;
        this.imageLink = imageLink;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getImageLink() {
        return imageLink;
    }
}
