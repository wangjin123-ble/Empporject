package com.entity;

public class Allbooks {
    String article;
    int ID;
    String name;
    String author;
    String variety;
    String time;
    String price;
    String buy_time;
    String place;
    String manager;
    String department;
    String introduce;

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBuy_time() {
        return buy_time;
    }

    public void setBuy_time(String buy_time) {
        this.buy_time = buy_time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Allbooks(String article, int ID, String name, String author, String variety, String time, String price, String buy_time, String place, String manager, String department, String introduce) {
        this.article = article;
        this.ID = ID;
        this.name = name;
        this.author = author;
        this.variety = variety;
        this.time = time;
        this.price = price;
        this.buy_time = buy_time;
        this.place = place;
        this.manager = manager;
        this.department = department;
        this.introduce = introduce;
    }

    public Allbooks() {
    }

    @Override
    public String toString() {
        return "Allbooks{" +
                "article='" + article + '\'' +
                ", ID=" + ID +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", variety='" + variety + '\'' +
                ", time='" + time + '\'' +
                ", price='" + price + '\'' +
                ", buy_time='" + buy_time + '\'' +
                ", place='" + place + '\'' +
                ", manager='" + manager + '\'' +
                ", department='" + department + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
