package com.billog.dayshock.model;

public class Article implements IArticle {

    private int image;
    private String title;
    private String desc;

    public Article(int image, String title, String desc) {
        this.image = image;
        this.title = title;
        this.desc = desc;
    }

    @Override
    public int getImage() {
        return image;
    }

    @Override
    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
