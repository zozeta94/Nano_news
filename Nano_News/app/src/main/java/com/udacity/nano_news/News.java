package com.udacity.nano_news;

/**
 * Created by zozeta on 26/09/2017.
 */
public class News {
    private String title;
    private String author;
    private String section;
    private String date;
    private String url;


    public News(String titleC, String authorC, String sectionC, String dateC ,String urlC) {
        this.title = titleC;
        this.author = authorC;
        this.section = sectionC;
        this.date = dateC;
        this.url=urlC;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSection() {
        return section;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
