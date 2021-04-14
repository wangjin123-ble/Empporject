package com.entity;

public class BorrowRecord {
    private int ID;
    private String apply_name;
    private String apply_time;
    private String apply_book;
    private String return_time;
    private String deadline;
    private int status;

    public BorrowRecord() {
    }

    public BorrowRecord(int ID, String apply_name, String apply_time, String apply_book, String return_time, String deadline, int status) {
        this.ID = ID;
        this.apply_name = apply_name;
        this.apply_time = apply_time;
        this.apply_book = apply_book;
        this.return_time = return_time;
        this.deadline = deadline;
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getApply_name() {
        return apply_name;
    }

    public void setApply_name(String apply_name) {
        this.apply_name = apply_name;
    }

    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public String getApply_book() {
        return apply_book;
    }

    public void setApply_book(String apply_book) {
        this.apply_book = apply_book;
    }

    public String getReturn_time() {
        return return_time;
    }

    public void setReturn_time(String return_time) {
        this.return_time = return_time;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BorrowRecord{" +
                "ID=" + ID +
                ", apply_name='" + apply_name + '\'' +
                ", apply_time='" + apply_time + '\'' +
                ", apply_book='" + apply_book + '\'' +
                ", return_time='" + return_time + '\'' +
                ", deadline='" + deadline + '\'' +
                ", status=" + status +
                '}';
    }
}
