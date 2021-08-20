package com.example.myapplication.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Dentist{

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	@SerializedName("articles")
	private List<ArticlesItem> articles;

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	public List<ArticlesItem> getArticles(){
		return articles;
	}
}