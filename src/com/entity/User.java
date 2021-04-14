package com.entity;


import java.util.List;

public class User {
    private int ID;
    private String name = "请设置";
    private String direction = "请设置";
    private String sex = "请设置";
    private int is_super_user=0;
    private String password;
    private int period_number = 5;
    private String student_number = "请设置";
    private String tel = "请设置";
    private String mail = "请设置";
    private List<Img> imgs;

    public List<Img> getImg() {
        return imgs;
    }

    public void setImg(List<Img> imgs) {
        this.imgs = imgs;
    }

    public User() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public User(int ID, String name, String direction, String sex, int is_super_user, String password, int period_number, String student_number, String tel, String mail) {
        this.ID = ID;
        this.name = name;
        this.direction = direction;
        this.sex = sex;
        this.is_super_user = is_super_user;
        this.password = password;
        this.period_number = period_number;
        this.student_number = student_number;
        this.tel = tel;
        this.mail = mail;
    }

    public User(int ID, String direction, String sex, int period_number, String student_number, String tel, String mail) {
        this.ID = ID;
        this.direction = direction;
        this.sex = sex;
        this.period_number = period_number;
        this.student_number = student_number;
        this.tel = tel;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getIs_super_user() {
        return is_super_user;
    }

    public void setIs_super_user(int is_super_user) {
        this.is_super_user = is_super_user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPeriod_number() {
        return period_number;
    }

    public void setPeriod_number(int period_number) {
        this.period_number = period_number;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", direction='" + direction + '\'' +
                ", sex='" + sex + '\'' +
                ", is_super_user=" + is_super_user +
                ", password='" + password + '\'' +
                ", period_number=" + period_number +
                ", student_number='" + student_number + '\'' +
                ", tel='" + tel + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
