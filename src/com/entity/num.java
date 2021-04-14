package com.entity;

public class num {
    private int pendingNum;
    private int solvedNum;

    public int getPendingNum() {
        return pendingNum;
    }

    public void setPendingNum(int pendingNum) {
        this.pendingNum = pendingNum;
    }

    public int getSolvedNum() {
        return solvedNum;
    }

    public void setSolvedNum(int solvedNum) {
        this.solvedNum = solvedNum;
    }

    @Override
    public String toString() {
        return "num{" +
                "pendingNum=" + pendingNum +
                ", solvedNum=" + solvedNum +
                '}';
    }
}
