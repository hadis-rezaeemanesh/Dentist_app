package com.example.myapplication.network;

import android.util.Log;

import com.example.myapplication.model.Category;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL = "https://portal.saminseo.ir/api/v1/";
    private static final String TAG = "REtrofitInstance";

    private static Retrofit retrofitInstance;

    public static Retrofit getRetrofit() {
        /*if (retrofitInstance == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .build();*/

            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofitInstance;
    }


 /*   public static Retrofit getArticleInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(createGsonConverter(
                        new TypeToken<List<>>() {}.getType(),
                        new getArticlesDeserialize()))
                .build();
    }*/

    public static Retrofit getCategoryInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(createGsonConverter(
                        new TypeToken<List<Category>>() {}.getType(),
                        new GetCategoryDeserializer()))
                .build();
    }

/*    public static Retrofit getRetrofitRelatedArticle(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(createGsonConverter(
                        Article.class,
                        new GetRelatedArticlesDeserializer()))
                .build();

    }*/

    private static Converter.Factory createGsonConverter(Type type, Object typeAdapter) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(type, typeAdapter);
        Gson gson = gsonBuilder.create();
        Log.d("TAG", "getRetrofitRelatedArticle: ");

        return GsonConverterFactory.create(gson);
    }

}
