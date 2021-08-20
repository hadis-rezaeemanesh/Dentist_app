package com.example.myapplication.viewModel;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.ArticlesItem;
import com.example.myapplication.model.BannersItem;
import com.example.myapplication.model.modelTest.CommentsItem;
import com.example.myapplication.model.modelTest.Item;
import com.example.myapplication.view.activity.ArticleActivity;
import com.example.myapplication.view.activity.CommentsActivity;
import com.example.myapplication.view.activity.EducationActivity;
import com.example.myapplication.repository.Repository;
import com.example.myapplication.model.Category;

import java.util.List;

public class ArticleViewModel extends AndroidViewModel {

    private Repository mRepository;
    private final LiveData<List<Category>> mLiveDataCategory;
    private LiveData<List<ArticlesItem>> mLiveDataArticleByCategory;
    private MutableLiveData<Integer> mClickedArticle;
    private MutableLiveData<Integer> mClickedCategory;
    private final LiveData<List<ArticlesItem>> mLiveDataRelatedArticles;
    private final LiveData<Item> mLiveDataArticle;
    private LiveData<List<CommentsItem>> mCommentsLiveData;
    private LiveData<List<ArticlesItem>> mSearchedLiveData;
    private LiveData<List<BannersItem>> mBannersLiveData;

    public LiveData<List<BannersItem>> getBannersLiveData() {
        return mBannersLiveData;
    }

    public LiveData<List<ArticlesItem>> getSearchedLiveData() {
        return mSearchedLiveData;
    }

    public LiveData<List<CommentsItem>> getCommentsLiveData() {
        return mCommentsLiveData;
    }

    public MutableLiveData<Integer> getClickedCategory() {
        mClickedCategory = new MutableLiveData<>();
        return mClickedCategory;
    }

    public LiveData<Item> getLiveDataArticle() {
        return mLiveDataArticle;
    }

    public LiveData<List<ArticlesItem>> getLiveDataRelatedArticles() {
        return mLiveDataRelatedArticles;
    }

    public MutableLiveData<Integer> getClickedArticle() {
        mClickedArticle = new MutableLiveData<>();
        return mClickedArticle;
    }

    public LiveData<List<ArticlesItem>> getLiveDataArticleByCategory() {
        return mLiveDataArticleByCategory;
    }

    public LiveData<List<Category>> getLiveDataCategory() {
        return mLiveDataCategory;
    }


    public ArticleViewModel(@NonNull Application application) {
        super(application);
        mRepository = Repository.getInstance();
        mLiveDataCategory = mRepository.getLiveDataCategories();
        mLiveDataArticleByCategory = mRepository.getLiveDataArticlesByCategory();
        mLiveDataRelatedArticles = mRepository.getLiveDataRelatedArticles();
        mLiveDataArticle = mRepository.getLiveDataArticle();
        mCommentsLiveData = mRepository.getCommentsLiveData();
        mSearchedLiveData = mRepository.getSearchedLiveData();
        mBannersLiveData = mRepository.getBannersLiveData();

    }
    public void getItems(){
        mRepository.getItems();
    }

    public void fetchCategory(){
        mRepository.fetchCategory();
    }

    public void fetchArticle(int id){
        mRepository.fetchArticle(id);
    }

    public void onArticleClicked(int id){
        mRepository.fetchArticle(id);
        Intent intent = ArticleActivity.newIntent(getApplication(), id);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent);

    }

    public void fetchArticleByCategory(int id){
        mRepository.fetchArticleByCategory(id);
    }

    public void onCategoryClicked(int id){
        mRepository.fetchArticleByCategory(id);
        mClickedCategory.setValue(id);
        /*Intent intent = EducationActivity.newIntent(getApplication(), id);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent);*/

    }

    public void onCommentClicked(int id){
        mRepository.getComments(id);
        Intent intent = CommentsActivity.newIntent(getApplication(), id);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent);
    }
    public void getComments(int id){
        mRepository.getComments(id);
    }

    public void getSearchedItems(String query){
        mRepository.getSearchedItem(query);
    }

    public void getBanners(){
        mRepository.getBanners();
    }
}
