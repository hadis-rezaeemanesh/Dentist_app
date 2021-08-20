package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class ArticlesItem{

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

	public ArticlesItem(String image, String description, String createdAt, int id, String title, String slug) {
		this.image = image;
		this.description = description;
		this.createdAt = createdAt;
		this.id = id;
		this.title = title;
		this.slug = slug;
	}

	public ArticlesItem() {
	}

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