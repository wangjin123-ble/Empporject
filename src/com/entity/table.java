package com.entity;

import java.sql.Date;

public class table {
    private int ID;             //申请表序号
    private String articles;    //物品名
    private long number;        //物品编号
    private String name;        //申请人姓名
    private String types;       //申请表种类
    private String pass;        //申请表状态
    private String nopass;
    private String allfun;
    private Date time;          //申请时间
    private long tel;            //申请人联系方式
    private Date examine_time;  //审批时间

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getArticles() {
        return articles;
    }

    public void setArticles(String articles) {
        this.articles = articles;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNopass() {
        return nopass;
    }

    public void setNopass(String nopass) {
        this.nopass = nopass;
    }

    public String getAllfun() {
        return allfun;
    }

    public void setAllfun(String allfun) {
        this.allfun = allfun;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public Date getExamine_time() {
        return examine_time;
    }

    public void setExamine_time(Date examine_time) {
        this.examine_time = examine_time;
    }

    @Override
    public String toString() {
        return "table{" +
                "ID=" + ID +
                ", articles='" + articles + '\'' +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", types='" + types + '\'' +
                ", pass='" + pass + '\'' +
                ", nopass='" + nopass + '\'' +
                ", allfun='" + allfun + '\'' +
                ", time=" + time +
                ", tel=" + tel +
                ", examine_time=" + examine_time +
                '}';
    }
}
