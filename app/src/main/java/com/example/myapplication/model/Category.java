package com.example.myapplication.model;

public class Category {
    private int mId;
    private String mNameCategory;
    private String mOrder;

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getNameCategory() {
        return mNameCategory;
    }

    public void setNameCategory(String mNameCategory) {
        this.mNameCategory = mNameCategory;
    }

    public String getOrder() {
        return mOrder;
    }

    public void setOrder(String mOrder) {
        this.mOrder = mOrder;
    }

    public Category() {
    }

    public Category(int Id, String NameCategory, String Order) {
        this.mId = Id;
        this.mNameCategory = NameCategory;
        this.mOrder = Order;
    }
}
