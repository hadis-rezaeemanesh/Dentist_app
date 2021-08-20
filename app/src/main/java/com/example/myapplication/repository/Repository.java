package com.example.myapplication.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.ArticlesItem;
import com.example.myapplication.model.Banners;
import com.example.myapplication.model.BannersItem;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Dentist;
import com.example.myapplication.model.modelTest.CommentsItem;
import com.example.myapplication.model.modelTest.Item;
import com.example.myapplication.model.modelTest.RelatedArticlesItem;
import com.example.myapplication.network.RetrofitInstance;
import com.example.myapplication.network.RetrofitService;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static RetrofitService service;

    private static Repository sInstance;
    private final MutableLiveData<List<Category>> mLiveDataCategories = new MutableLiveData<>();
    private final MutableLiveData<List<ArticlesItem>> mLiveDataArticlesByCategory = new MutableLiveData<>();
    private final MutableLiveData<List<ArticlesItem>> mLiveDataRelatedArticles = new MutableLiveData<>();
    private final MutableLiveData<Item> mLiveDataArticle = new MutableLiveData<>();
    private final MutableLiveData<List<CommentsItem>> mCommentsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<ArticlesItem>> mSearchedLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<BannersItem>> mBannersLiveData = new MutableLiveData<>();

    public MutableLiveData<List<BannersItem>> getBannersLiveData() {
        return mBannersLiveData;
    }

    public MutableLiveData<List<ArticlesItem>> getSearchedLiveData() {
        return mSearchedLiveData;
    }

    public MutableLiveData<List<CommentsItem>> getCommentsLiveData() {
        return mCommentsLiveData;
    }

    public MutableLiveData<Item> getLiveDataArticle() {
        return mLiveDataArticle;
    }

    public MutableLiveData<List<ArticlesItem>> getLiveDataRelatedArticles() {
        return mLiveDataRelatedArticles;
    }

    public static Repository getInstance(){
        if (sInstance == null)
            sInstance = new Repository();
        return sInstance;
    }

    public MutableLiveData<List<ArticlesItem>> getLiveDataArticlesByCategory() {
        return mLiveDataArticlesByCategory;
    }

    public MutableLiveData<List<Category>> getLiveDataCategories() {
        return mLiveDataCategories;
    }

    private Repository(){

//        mService = RetrofitInstance.getArticleInstance().create(RetrofitService.class);
        service = RetrofitInstance.getCategoryInstance().create(RetrofitService.class);
//        mServiceArticle = RetrofitInstance.getRetrofitRelatedArticle().create(RetrofitService.class);
    }

    public void getItems(){
        Call<List<ArticlesItem>> call = RetrofitInstance.getRetrofit().create(RetrofitService.class).getArticles();

        call.enqueue(new Callback<List<ArticlesItem>>() {
            @Override
            public void onResponse(Call<List<ArticlesItem>> call, Response<List<ArticlesItem>> response) {
                List<ArticlesItem> mItems = response.body();
                mLiveDataArticlesByCategory.setValue(mItems);
            }

            @Override
            public void onFailure(Call<List<ArticlesItem>> call, Throwable t) {

            }
        });
    }

    public void fetchCategory(){
        Call<List<Category>> call = service.getItems();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categoriesItems = response.body();
                mLiveDataCategories.setValue(categoriesItems);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

    public void fetchArticleByCategory(int id){
        Call<Dentist> call =
                RetrofitInstance.getRetrofit().create(RetrofitService.class).getArticleByCategory(id);
        call.enqueue(new Callback<Dentist>() {
            @Override
            public void onResponse(Call<Dentist> call, Response<Dentist> response) {
               Dentist dentist = response.body();
               mLiveDataArticlesByCategory.setValue(dentist.getArticles());
            }

            @Override
            public void onFailure(Call<Dentist> call, Throwable t) {
            }
        });
    }

    public void fetchArticle(int id){
        Call<Item> call = RetrofitInstance
                .getRetrofit().
                        create(RetrofitService.class).
                        getItem(String.valueOf(id));
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful()){
                    Item item = response.body();
                    mLiveDataArticle.setValue(item);
                    assert item != null;
                    List<ArticlesItem> items = new ArrayList<>();
                    for (RelatedArticlesItem relatedArticlesItem: item.getRelatedArticles()){
                        ArticlesItem article = new ArticlesItem(
                                relatedArticlesItem.getImage(),
                                relatedArticlesItem.getDescription(),
                                relatedArticlesItem.getCreatedAt(),
                                relatedArticlesItem.getId(),
                                relatedArticlesItem.getTitle(),
                                relatedArticlesItem.getSlug()
                        );
                        items.add(article);
                    }
                    mLiveDataRelatedArticles.setValue(items);

                }
            }
            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<Item> getArticle(int id){
        MutableLiveData<Item> articleMutableLiveData = new MutableLiveData<>();
        RetrofitInstance.getRetrofit().create(RetrofitService.class)
                .getItem(String.valueOf(id)).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful())
                    articleMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
        return articleMutableLiveData;
    }

    public void getComments(int id){
        Call<Item> call = RetrofitInstance
                .getRetrofit().
                        create(RetrofitService.class).
                        getItem(String.valueOf(id));
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
               Item item = response.body();
                mCommentsLiveData.setValue(item.getComments());

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }

    public void getSearchedItem(String query){
        Call<List<ArticlesItem>> call = RetrofitInstance.
                getRetrofit().
                create(RetrofitService.class).
                getSearchedArticles(query);
        call.enqueue(new Callback<List<ArticlesItem>>() {
            @Override
            public void onResponse(Call<List<ArticlesItem>> call, Response<List<ArticlesItem>> response) {
                List<ArticlesItem> items = response.body();
                mSearchedLiveData.setValue(items);
            }

            @Override
            public void onFailure(Call<List<ArticlesItem>> call, Throwable t) {

            }
        });
    }

    public void getBanners(){
        Call<Banners> call =
                RetrofitInstance.getRetrofit().create(RetrofitService.class).getBanners();
        call.enqueue(new Callback<Banners>() {
            @Override
            public void onResponse(Call<Banners> call, Response<Banners> response) {
                Banners banners = response.body();
                mBannersLiveData.setValue(banners.getBanners());
            }

            @Override
            public void onFailure(Call<Banners> call, Throwable t) {

            }
        });
    }
}
