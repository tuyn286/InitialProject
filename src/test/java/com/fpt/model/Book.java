package com.fpt.model;

import java.util.Date;

public class Book {
    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    private Date publishDate;
    private String publisher;
    private int pages;
    private String description;
    private String website;

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPages() {
        return pages;
    }

    public String getDescription() {
        return description;
    }

    public String getWebsite() {
        return website;
    }
}
