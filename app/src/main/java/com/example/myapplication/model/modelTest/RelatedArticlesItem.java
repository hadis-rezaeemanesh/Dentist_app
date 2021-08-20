package com.example.myapplication.model.modelTest;

import com.google.gson.annotations.SerializedName;

public class RelatedArticlesItem{

	@SerializedName("image")
	private String image;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("slug")
	private String slug;

	public String getImage(){
		return image;
	}

	public String getDescription(){
		return description;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getSlug(){
		return slug;
	}
}