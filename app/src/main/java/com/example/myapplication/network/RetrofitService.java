package com.example.myapplication.network;

import com.example.myapplication.model.ArticlesItem;
import com.example.myapplication.model.Banners;
import com.example.myapplication.model.BannersItem;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Dentist;
import com.example.myapplication.model.modelTest.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("articles? api_token=HQKhMMAL6FDQIU2PDMJtKpkAbtCxsytUaBEjUr8uWJIPeIGugoQs37kVt5XEUi4berdZEpOkvBZMrC3dHXUffZexlsmSBZUDyGdq")
    Call<List<ArticlesItem>> getArticles();

    @GET("articles?api_token=HQKhMMAL6FDQIU2PDMJtKpkAbtCxsytUaBEjUr8uWJIPeIGugoQs37kVt5XEUi4berdZEpOkvBZMrC3dHXUffZexlsmSBZUDyGdq")
    Call<List<Category>> getItems();

    @GET("articles/{id}?api_token=HQKhMMAL6FDQIU2PDMJtKpkAbtCxsytUaBEjUr8uWJIPeIGugoQs37kVt5XEUi4berdZEpOkvBZMrC3dHXUffZexlsmSBZUDyGdq")
    Call<Dentist> getArticleByCategory(@Path("id") int id);

    @GET("article/{id}?api_token=HQKhMMAL6FDQIU2PDMJtKpkAbtCxsytUaBEjUr8uWJIPeIGugoQs37kVt5XEUi4berdZEpOkvBZMrC3dHXUffZexlsmSBZUDyGdq")
    Call<Item> getItem(@Path("id") String id);

    @GET("articles?api_token=HQKhMMAL6FDQIU2PDMJtKpkAbtCxsytUaBEjUr8uWJIPeIGugoQs37kVt5XEUi4berdZEpOkvBZMrC3dHXUffZexlsmSBZUDyGdq")
    Call<List<ArticlesItem>> getSearchedArticles(@Query("search") String search);

    @GET(".?api_token=HQKhMMAL6FDQIU2PDMJtKpkAbtCxsytUaBEjUr8uWJIPeIGugoQs37kVt5XEUi4berdZEpOkvBZMrC3dHXUffZexlsmSBZUDyGdq")
    Call<Banners> getBanners();
}
