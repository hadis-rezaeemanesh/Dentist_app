package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class CategoriesItem{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("order")
	private String order;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getOrder(){
		return order;
	}
}