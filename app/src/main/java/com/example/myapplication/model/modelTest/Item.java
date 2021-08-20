package com.example.myapplication.model.modelTest;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Item {

	@SerializedName("image")
	private String image;

	@SerializedName("comments")
	private List<CommentsItem> comments;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("related_articles")
	private List<RelatedArticlesItem> relatedArticles;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	public String getImage(){
		return image;
	}

	public List<CommentsItem> getComments(){
		return comments;
	}

	public String getDescription(){
		return description;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public List<RelatedArticlesItem> getRelatedArticles(){
		return relatedArticles;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}
}