package com.example.myapplication.model.modelTest;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CommentsItem{

	@SerializedName("star")
	private String star;

	@SerializedName("replies")
	private List<Object> replies;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("comment")
	private String comment;

	@SerializedName("user")
	private String user;

	public String getStar(){
		return star;
	}

	public List<Object> getReplies(){
		return replies;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getComment(){
		return comment;
	}

	public String getUser(){
		return user;
	}
}