package com.entity;

public class Img {
    private int user_id;
    private String image_path;
    private String old_name;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getOld_name() {
        return old_name;
    }

    public void setOld_name(String old_name) {
        this.old_name = old_name;
    }

    public Img() {
    }

    public Img(int user_id, String image_path, String old_name) {
        this.user_id = user_id;
        this.image_path = image_path;
        this.old_name = old_name;
    }

    @Override
    public String toString() {
        return "Img{" +
                "user_id=" + user_id +
                ", image_path='" + image_path + '\'' +
                ", old_name='" + old_name + '\'' +
                '}';
    }
}
