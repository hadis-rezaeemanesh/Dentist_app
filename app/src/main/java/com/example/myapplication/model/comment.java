package com.example.myapplication.model;

import java.util.Date;

public class comment {
    private int mId;
    private String mNameUser;
    private Date mDate;
    private int mRate;
    private String mCommentUser;

    public comment() {

    }

    public String getNameUser() {
        return mNameUser;
    }

    public void setNameUser(String mNameUser) {
        this.mNameUser = mNameUser;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public int getRate() {
        return mRate;
    }

    public void setRate(int mRate) {
        this.mRate = mRate;
    }

    public String getCommentUser() {
        return mCommentUser;
    }

    public void setCommentUser(String mCommentUser) {
        this.mCommentUser = mCommentUser;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }
}
