package com.example.myapplication.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Banners{

	@SerializedName("banners")
	private List<BannersItem> banners;

	public List<BannersItem> getBanners(){
		return banners;
	}
}