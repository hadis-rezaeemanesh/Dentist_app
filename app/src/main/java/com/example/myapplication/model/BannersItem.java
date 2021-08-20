package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class BannersItem{

	@SerializedName("image")
	private String image;

	@SerializedName("link")
	private String link;

	@SerializedName("order")
	private String order;

	public String getImage(){
		return image;
	}

	public String getLink(){
		return link;
	}

	public String getOrder(){
		return order;
	}
}